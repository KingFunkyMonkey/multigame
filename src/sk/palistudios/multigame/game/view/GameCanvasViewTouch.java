package sk.palistudios.multigame.game.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import sk.palistudios.multigame.game.GameActivity;
import sk.palistudios.multigame.game.minigames.BaseMiniGame;
import sk.palistudios.multigame.game.minigames.MinigamesManager;
import sk.palistudios.multigame.game.persistence.GameSharedPref;

/**
 * @author Pali
 */
public class GameCanvasViewTouch extends BaseGameCanvasView {
  public interface userInteractedTouchListener {
    void onUserInteractedTouch(float x, float y);
  }
  
  private GameActivity mGame;

  public GameCanvasViewTouch(Context context) {
    this(context, null, 0);
  }

  public GameCanvasViewTouch(Context context, AttributeSet attrs){
    this(context, attrs, 0);
  }

  public GameCanvasViewTouch(Context context, AttributeSet attrs, int defStyle){
    super(context, attrs, defStyle);
  }

  @Override
  public void attachMinigame(final BaseMiniGame minigame, final int position) {
    super.attachMinigame(minigame, position);
    mGame = mMiniGame.mGame;

    setOnTouchListener(new OnTouchListener() {
      @Override
      public boolean onTouch(final View view, final MotionEvent event) {

        if (MinigamesManager.isMiniGameActive(position) && !mGame.isGameStopped()) {
          ((userInteractedTouchListener) mMiniGame).onUserInteractedTouch(event.getX(),
              event.getY());
          return true;
        }

        if (event.getAction() == MotionEvent.ACTION_DOWN && mGame.gameStopped &&
            !GameSharedPref.isTutorialModeActivated()) {
          mGame.startGame();
        }

        return true;
      }
    });

  }

  @Override
  public void detachMinigame(){
    mGame = null;
    mMiniGame = null;
  }
}
