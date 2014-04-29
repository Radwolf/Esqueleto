package com.esqueleto.esqueletoui.builder;

import com.esqueleto.esqueletosdk.model.Cuenta;
import com.esqueleto.esqueletoui.renderer.CuentaRenderer;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by rgonzalez on 25/04/2014.
 */
public class CuentaRendererBuilder  extends RendererBuilder<Cuenta> {

    @Inject
    public CuentaRendererBuilder(List<Renderer<Cuenta>> prototypes) {
        super(prototypes);
    }

    /**
     * Method to declare Video-VideoRenderer mapping.
     * Favorite videos will be rendered using FavoriteVideoRenderer.
     * Live videos will be rendered using LiveVideoRenderer.
     * Liked videos will be rendered using LikeVideoRenderer.
     *
     * @param content used to map object-renderers.
     * @return VideoRenderer subtype class.
     */
    @Override
    protected Class getPrototypeClass(Cuenta content) {
        Class prototypeClass = CuentaRenderer.class;
        return prototypeClass;
    }

}