/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokerserver;

/**
 *
 * @author supithaweerasinghe
 */
public class PointsDetails {
    private int id;
    private int points;
    private String name;
    private String combination;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the combination
     */
    public String getCombination() {
        return combination;
    }

    /**
     * @param combination the combination to set
     */
    public void setCombination(String combination) {
        this.combination = combination;
    }
}
