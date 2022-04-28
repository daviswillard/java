package edu.school21.utils;

public class RendererStandardImpl implements Renderer {
    private final PreProcessor preProc;

    public RendererStandardImpl(PreProcessor preProc) {
        this.preProc = preProc;
    }

    @Override
    public void render(String text) {
        text = preProc.process(text);
        System.out.println(text);
    }
}
