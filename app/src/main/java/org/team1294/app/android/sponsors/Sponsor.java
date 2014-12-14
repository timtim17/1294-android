package org.team1294.app.android.sponsors;

public class Sponsor {
    private String sponsorName;
    private String sponsorWebsite;
    private int sponsorLogo;

    public Sponsor(String sponsorName, String sponsorWebsite, int sponsorLogo){
        this.sponsorName = sponsorName;
        this.sponsorWebsite = sponsorWebsite;
        this.sponsorLogo = sponsorLogo;
    }

    @Override
    public String toString(){
        return getName();
    }

    public String getName(){
        return this.sponsorName;
    }
    public String getWebsite(){
        return this.sponsorWebsite;
    }
    public int getLogo(){
        return this.sponsorLogo;
    };
}
