package edu.school21.utils;

import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements Printer {
    private final Renderer  renderer;
    private LocalDateTime   dateTime;

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
        this.dateTime = LocalDateTime.now();
    }

    @Override
    public void print(String text) {
        text = dateTime.toString() + text;
        renderer.render(text);
    }

    @Override
    public boolean  equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PrinterWithDateTimeImpl)) {
            return false;
        }
        PrinterWithDateTimeImpl comp = (PrinterWithDateTimeImpl) obj;
        return dateTime.equals(comp.dateTime) & renderer.equals(comp.renderer);
    }
}
