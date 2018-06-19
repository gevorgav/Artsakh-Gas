package portfolio;

import Core.Models.Country;
import Core.Models.Month;
import Core.Models.Year;
import Core.Root;
import Core.Util;
import Models.Client;
import Models.Sights;
import Models.Transport;
import dao.ClientDao;
import home.HomeForm;
import hotel.Hotel;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by arshak.askaryan on 1/25/2017.
 */
public class PortfolioForm {
    private List<Client> clients;
    private List<Client> filteredClients;

    public PortfolioForm() {
    }

    @Autowired
    private ClientDao clientDao;

    public List<Client> getClients() {
        if(clients == null){
            this.clients = clientDao.loadAll();
        }
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Client> getFilteredClients() {
        return filteredClients;
    }

    public void setFilteredClients(List<Client> filteredClients) {
        this.filteredClients = filteredClients;
    }
}
