package edu.school21.utils;

public class PrinterWithPrefixImpl implements Printer {
    private String          prefix;
    private final Renderer  renderer;

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void print(String text) {
        text = prefix + text;
        renderer.render(text);
    }

    @Override
    public boolean  equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PrinterWithPrefixImpl)) {
            return false;
        }
        PrinterWithPrefixImpl comp = (PrinterWithPrefixImpl) obj;
        return prefix.equals(comp.prefix) & renderer.equals(comp.renderer);
    }

}
