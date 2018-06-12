package Beans;

import Core.Models.Region;
import dao.RegionDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestForm {
    @Autowired
    private RegionDao regionDao;

    private List<Region> regionList;

    public List<Region> getRegionList() {
        if(regionList == null){
            regionList = this.regionDao.findAll();
        }
        return regionList;
    }

    public void setRegionList(List<Region> regionList) {
        this.regionList = regionList;
    }
}
