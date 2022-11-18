package com.mikitellurium.smashcharacterreoulette;

import javax.swing.*;
import java.awt.*;

public class GradientButton extends JButton {
    private Color bottomColor;
    private Color topColor;
    private boolean isGradient;

    public GradientButton() {
        super();
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (isGradient) {
        final Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(new GradientPaint(
                    new Point(0, 0), bottomColor,
                    new Point(0, getHeight()), topColor));

            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.dispose();
        }
        super.paintComponent(g);
    }

    public void setBackgroundGradient(Color bottomColor, Color topColor) {
        this.bottomColor = bottomColor;
        this.topColor = topColor;
        if (!isGradient) {
            isGradient = true;
        }
    }

    public void removeGradient() {
        if (isGradient) {
            isGradient = false;
        }
    }
}

