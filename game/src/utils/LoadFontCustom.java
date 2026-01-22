package utils;

import java.awt.*;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class LoadFontCustom {

    private static final Map<String, Font> fontCache = new ConcurrentHashMap<>();

    public static Font loadCustomFont(String path, float size) {
        var font = fontCache.get(path);

        if (font == null) {
            try {
                font = Font.createFont(
                        Font.TRUETYPE_FONT,
                        Objects.requireNonNull(LoadFontCustom.class.getResourceAsStream(path))
                );
                var ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(font);
                fontCache.put(path, font);

            } catch (IOException | FontFormatException e) {
                throw new RuntimeException("Erro ao carregar font customizada: " + e);
            }
        }
        return font.deriveFont(size);
    }
}
