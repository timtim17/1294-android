package org.team1294.app.android.fonts;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;

public class Fonts {
    public Typeface robotoThin;

    public Fonts(Context c) {
        AssetManager mngr = c.getAssets();
        robotoThin = Typeface.createFromAsset(mngr, "fonts/Roboto-Thin.ttf");
    }
}
