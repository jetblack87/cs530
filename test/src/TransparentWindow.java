import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TransparentWindow {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                MyFrame frame = new MyFrame();
                frame.setUndecorated(true);

                String version = System.getProperty("java.version");
                if (version.startsWith("1.7")) {


                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    GraphicsDevice graphicsDevice = ge.getDefaultScreenDevice();

                    System.out.println("Transparent from under Java 7");
                    /* This won't run under Java 6, uncomment if you are using Java 7
                    System.out.println("isPerPixelAlphaTranslucent = " + graphicsDevice.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSLUCENT));
                    System.out.println("isPerPixelAlphaTransparent = " + graphicsDevice.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSPARENT));
                    System.out.println("isPerPixelAlphaTranslucent = " + graphicsDevice.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.TRANSLUCENT));
                    */
                    frame.setBackground(new Color(0, 0, 0, 0));

                } else if (version.startsWith("1.6")) {

                    System.out.println("Transparent from under Java 6");
                    System.out.println("isPerPixelAlphaSupported = " + supportsPerAlphaPixel());
                    setOpaque(frame, false);

                }

                frame.setSize(400, 400);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

            }
        });

    }

    public static class MyFrame extends JFrame {

        public MyFrame() throws HeadlessException {

            setContentPane(new MyContentPane());
            setDefaultCloseOperation(EXIT_ON_CLOSE);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    if (e.getClickCount() == 2) {

                        dispose();

                    }

                }
            });

        }
    }

    public static class MyContentPane extends JPanel {

        public MyContentPane() {

            setLayout(new GridBagLayout());
            add(new JLabel("Hello, I'm a transparent frame under Java " + System.getProperty("java.version")));

            setOpaque(false);

        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(Color.BLUE);

            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

        }
    }

    public static boolean supportsPerAlphaPixel() {

        boolean support = false;

        try {

            Class<?> awtUtilsClass = Class.forName("com.sun.awt.AWTUtilities");
            support = true;

        } catch (Exception exp) {
        }

        return support;

    }

    public static void setOpaque(Window window, boolean opaque) {

        try {

            Class<?> awtUtilsClass = Class.forName("com.sun.awt.AWTUtilities");
            if (awtUtilsClass != null) {

                Method method = awtUtilsClass.getMethod("setWindowOpaque", Window.class, boolean.class);
                method.invoke(null, window, opaque);
//                com.sun.awt.AWTUtilities.setWindowOpaque(this, opaque);
//                ((JComponent) window.getContentPane()).setOpaque(opaque);

            }

        } catch (Exception exp) {
        }

    }

    public static void setOpacity(Window window, float opacity) {

        try {

            Class<?> awtUtilsClass = Class.forName("com.sun.awt.AWTUtilities");
            if (awtUtilsClass != null) {

                Method method = awtUtilsClass.getMethod("setWindowOpacity", Window.class, float.class);
                method.invoke(null, window, opacity);

            }

        } catch (Exception exp) {

            exp.printStackTrace();

        }

    }

    public static float getOpacity(Window window) {

        float opacity = 1f;
        try {

            Class<?> awtUtilsClass = Class.forName("com.sun.awt.AWTUtilities");
            if (awtUtilsClass != null) {

                Method method = awtUtilsClass.getMethod("getWindowOpacity", Window.class);
                Object value = method.invoke(null, window);
                if (value != null && value instanceof Float) {

                    opacity = ((Float) value).floatValue();

                }

            }

        } catch (Exception exp) {

            exp.printStackTrace();

        }


        return opacity;

    }
}