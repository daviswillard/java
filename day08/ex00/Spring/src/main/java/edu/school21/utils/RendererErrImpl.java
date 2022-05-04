package edu.school21.utils;

public class RendererErrImpl implements Renderer {
    private final PreProcessor preProc;

    public RendererErrImpl(PreProcessor preProc) {
        this.preProc = preProc;
    }

    @Override
    public void render(String text) {
        text = preProc.process(text);
        System.err.println(text);
    }
}
