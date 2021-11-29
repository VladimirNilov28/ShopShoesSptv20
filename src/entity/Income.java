/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Mad Max
 */
@Entity
public class Income implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private int income;
    
    private double generalMoney;

    public double getGeneralMoney() {
        return generalMoney;
    }

    public void setGeneralMoney(double generalMoney) {
        this.generalMoney = generalMoney;
    }
    
    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}