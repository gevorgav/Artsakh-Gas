package hotel;

import Core.FileUpload;
import Core.Interface.Form;
import Core.Root;
import Core.Util;
import org.primefaces.context.RequestContext;
import portfolio.Portfoliohotels;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Created by arshak.askaryan on 1/25/2017.
 */
public class HotelForm implements Form, Serializable {
    private Root root;
    private List<Hotel> hotels;
    private Hotel hotel;
    private List<HotelType> hotelTypes;
    private Integer hotelTypeIdForFind = 1;
    private List<Hotel> hotelsWithType;
    private Part file;

    public Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
    }

    public List<Hotel> getHotels() {
        if (this.hotels == null) {
            this.hotels = getRoot().getHotelDao().getAll();
        }
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<HotelType> getHotelTypes() {
        if (this.hotelTypes == null) {
            this.hotelTypes = getRoot().getHotelDao().getHotelTypes();
        }
        return hotelTypes;
    }

    public Integer getHotelTypeIdForFind() {
        return hotelTypeIdForFind;
    }

    public void setHotelTypeIdForFind(Integer hotelTypeIdForFind) {
        this.hotelTypeIdForFind = hotelTypeIdForFind;
    }

    public List<Hotel> getHotelsWithType() {
        if (this.hotelsWithType == null) {
            this.hotelsWithType = getRoot().getHotelDao().getHotelsByTypeId(this.hotelTypeIdForFind);
        }
        return hotelsWithType.stream().sorted(Comparator.comparing(Hotel::getRate).reversed()).collect(Collectors.toList());
    }

    public void setHotelsWithType(List<Hotel> hotelsWithType) {
        this.hotelsWithType = hotelsWithType;
    }

    public void setHotelTypes(List<HotelType> hotelTypes) {
        this.hotelTypes = hotelTypes;
    }

    public void save() {
        if (file != null) {
            String imageWay = "";
            try {
                imageWay = Util.getBean("fileUpload", FileUpload.class).upload(this.file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.hotel.setPhotoWay(imageWay);
        }

        if (this.hotel.getId() != null) {
            if (file != null) {
                deleteOldFile(this.hotel.getId());
            }
            if (getRoot().getHotelDao().update(this.hotel)) {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                FacesMessage facesMessage = new FacesMessage("Փոփոխությունը Հաջողությամբ պահպանվել է");
                facesContext.addMessage(null, facesMessage);
            }
        } else {
            if (getRoot().getHotelDao().insert(this.hotel)) {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                FacesMessage facesMessage = new FacesMessage("Հաջողությամբ պահպանվել է");
                for (Hotel hotel : root.getHotelDao().getAll()) {
                    if (hotel.getName().equals(this.hotel.getName())) {
                        this.setHotel(hotel);
                        Util.getBean("hotel", Hotel.class).setId(hotel.getId());
                    }
                }
                facesContext.addMessage(null, facesMessage);
            }
        }

        this.hotelsWithType = null;
        this.reloadPage();

    }

    public Integer total(){
        return getRoot().getHotelDao().getAll().size() ;
    }

    public Integer totalBadCount(){
        Integer count =0;
        for (Hotel item:getRoot().getHotelDao().getHotelsByTypeId(this.getHotelTypeIdForFind())) {
            count += item.getBedCount();
        }
        return count;
    }

    private void reloadPage(){
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteOldFile(Integer id) {
        Properties prop = new Properties();
        String url = "";
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
            prop.load(input);
            url = prop.getProperty("fileUploadUrl");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Hotel oldHotel = getRoot().getHotelDao().getById(id);
        if (oldHotel.getPhotoWay() == null) {
            return true;
        }
        File file = new File(url + oldHotel.getPhotoWay());
        if (file.delete()) {
            return true;
        } else {
            return false;
        }
    }

    public void addNew() {
        this.hotel = new Hotel();
        this.file = null;
    }

    public void changeHotelType() {
        this.hotelsWithType = getRoot().getHotelDao().getHotelsByTypeId(this.hotelTypeIdForFind);
        this.total();
        RequestContext.getCurrentInstance().update("hotelTotalId");
        RequestContext.getCurrentInstance().execute("reload_js('scripts/app.js')");
//        this.hotelsWithType.clear();
//        this.hotelsWithType.addAll(getRoot().getHotelDao().getHotelsByTypeId(this.hotelTypeIdForFind));
    }

    public void edit(Integer id) {
        this.setHotel(getRoot().getHotelDao().getById(id));
        Util.getBean("hotel", Hotel.class).setId(hotel.getId());
    }

    public void delete(Integer id) {
        if (Objects.equals(this.getHotel().getId(),id)) {
            this.hotel = new Hotel();
        }
        this.hotelsWithType = null;
        if (deleteOldFile(id)) {
            if (this.getRoot().getHotelDao().delete(id)) {
                for(Portfoliohotels portfoliohotels: getRoot().getPortfolioDao().getPortfoliohotels()){
                    if(portfoliohotels.getHotelid().equals(id)){
                        this.getRoot().getPortfolioDao().deletePortfoliohotels(portfoliohotels.getId());
                    }
                }
                FacesContext facesContext = FacesContext.getCurrentInstance();
                FacesMessage facesMessage = new FacesMessage("Հաջողությամբ ջնջվել է");
                facesContext.addMessage(null, facesMessage);
            }
        }
    }



}
