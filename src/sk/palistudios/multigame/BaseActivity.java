/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.palistudios.multigame;

import android.app.Activity;
import sk.palistudios.multigame.tools.SoundEffectsCenter;

/**
 * @author virdzek
 */
public abstract class BaseActivity extends Activity {


    @Override
    protected void onResume() {
        super.onResume();
        SoundEffectsCenter.muteSystemSounds(this, true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SoundEffectsCenter.muteSystemSounds(this, false);
    }


}
