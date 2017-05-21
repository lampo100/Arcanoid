package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.controller.Controller;
import com.mygdx.game.model.ModelManager;

/**
 * <h1>ArcanoidGame</h1>
 * This is main game class that is also main view class. When the game
 * is loaded game cycle begins here.
 * @author Kacper Kamiński
 */
public class ArcanoidGame extends Game{

    private final ModelManager model;
    private final Controller controller;

    /**
     *
     * @param model
     * Model containing data for our view.
     * @param controller
     * Controller that will handle input.
     */
    public ArcanoidGame(ModelManager model, Controller controller){
        super();
        this.model = model;
        this.controller = controller;
        this.controller.setGame(this);
        this.controller.createManagers();
    }

    public ModelManager getModel() {
        return model;
    }

    public Controller getController() {
        return controller;
    }

    @Override
    public void create() {
        model.initializeModelManager();
        controller.getScreenManager().changeScreen("mainMenu");
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        model.dispose();
        controller.dispose();
    }

}
