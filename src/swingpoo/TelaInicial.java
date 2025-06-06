package swingpoo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;

public class TelaInicial {

    public static void montarTelaInicial() throws IOException {

        final JFrame oJFrameInicial = new JFrame("Tela Inicial");

        oJFrameInicial.setBounds(200, 170, 500, 200);
        oJFrameInicial.setLayout(null);

        JButton oJButtonTela1 = new JButton("Tela 1");
        oJButtonTela1.setBounds(60, 60, 100, 30);
        oJFrameInicial.add(oJButtonTela1);

        JButton oJButtonTela2 = new JButton("Tela 2");
        oJButtonTela2.setBounds(200, 60, 100, 30);
        oJFrameInicial.add(oJButtonTela2);

        JButton oJButtonTela3 = new JButton("Tela 3");
        oJButtonTela3.setBounds(350, 60, 100, 30);
        oJFrameInicial.add(oJButtonTela3);

        oJButtonTela1.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                try {
                    oJFrameInicial.dispose();
                    Tela.montarTela();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    System.out.println(e1.getMessage());
                }

            }
        });

        oJFrameInicial.setVisible(true);

    }
}
