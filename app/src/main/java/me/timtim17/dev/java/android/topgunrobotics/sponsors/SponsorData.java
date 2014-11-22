package me.timtim17.dev.java.android.topgunrobotics.sponsors;

import java.util.ArrayList;
import java.util.List;

import me.timtim17.dev.java.android.topgunrobotics.R;
import me.timtim17.dev.java.android.topgunrobotics.sponsors.Sponsor;

public class SponsorData {
    private List<Sponsor> Sponsors = new ArrayList<Sponsor>();
    public List<Sponsor> getSponsors(){
        return Sponsors;
    }

    public SponsorData(){
        Sponsors.add(new Sponsor("Boeing", "http://www.boeing.com/boeing/", R.drawable.sponsors_boeing));
        Sponsors.add(new Sponsor("SAE Northwest", "http://www.sae.org/servlets/sectionInfo?SECTION_CODE=MS049&OBJECT_TYPE=sectionInfo&PAGE=getSectionMainPage", R.drawable.sponsors_sae));
        Sponsors.add(new Sponsor("Lake Washington Schools Foundation", "http://www.lwsf.org/", R.drawable.sponsor_lwsf));
    }
}
