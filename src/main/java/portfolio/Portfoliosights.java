package portfolio;

import Core.Root;
import Models.Sights;

import javax.faces.context.FacesContext;
import java.util.stream.Collectors;

public class Portfoliosights {
  private Integer id;
  private Integer portfolioid;
  private Integer stightsid;
  private Sights sights;
  private Integer count;
  private Root root;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getPortfolioid() {
    return portfolioid;
  }

  public void setPortfolioid(Integer portfolioid) {
    this.portfolioid = portfolioid;
  }

  public Integer getStightsid() {
    return stightsid;
  }

  public void setStightsid(Integer stightsid) {
    this.stightsid = stightsid;
  }

  public Sights getSights() {
    return getRoot().getPortfolioDao().getSightses().stream().filter(x -> stightsid.equals(x.getId())).findFirst().get();
  }

  public void setSights(Sights sights) {
    this.sights = sights;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public Root getRoot() {
    FacesContext context = FacesContext.getCurrentInstance();
    Root bean = context.getApplication().evaluateExpressionGet(context, "#{root}", Root.class);
    return bean;
  }

  public void setRoot(Root root) {
    this.root = root;
  }
}
