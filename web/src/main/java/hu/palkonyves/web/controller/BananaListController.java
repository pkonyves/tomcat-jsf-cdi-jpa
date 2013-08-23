package hu.palkonyves.web.controller;

import hu.palkonyves.business.service.BananaService;
import hu.palkonyves.persistence.entities.Banana;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class BananaListController {

    private String newBananaName;

    @Inject
    BananaService bananaService;

    public List<Banana> getBananas() {
	List<Banana> result = (List<Banana>) bananaService.getAllBananas();
	return result;
    }

    public void createBanana() {
	Banana b = new Banana();
	b.setBrowness(0);
	b.setYellowness(100);
	b.setName(newBananaName);
	bananaService.createBanana(b);
    }

    public String getNewBananaName() {
	return newBananaName;
    }

    public void setNewBananaName(String newBananaName) {
	this.newBananaName = newBananaName;
    }
}
