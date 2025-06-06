package swingpoo;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class Tela {

    public static void montarTela() throws IOException {

        final JFrame oJFrame = new JFrame("Cadastro de Cliente");

        oJFrame.setBounds(450, 170, 580, 500);
        oJFrame.setLayout(null);

        JLabel oJLabelCPF = new JLabel("CPF:");
        oJLabelCPF.setBounds(10, 10, 60, 25);
        oJLabelCPF.setHorizontalAlignment(JLabel.RIGHT);
        oJFrame.add(oJLabelCPF);

        JFormattedTextField oJTextFieldCPF = new JFormattedTextField(mascara("###.###.###-##"));
        oJTextFieldCPF.setBounds(75, 10, 120, 25);
        oJTextFieldCPF.setLayout(null);
        oJFrame.add(oJTextFieldCPF);

        JLabel oJLabelNome = new JLabel("Nome:");
        oJLabelNome.setBounds(10, 40, 60, 25);
        oJLabelNome.setHorizontalAlignment(JLabel.RIGHT);
        oJFrame.add(oJLabelNome);

        final JTextField oJTextFieldNome = new JTextField();
        oJTextFieldNome.setBounds(75, 40, 400, 25);
        oJTextFieldNome.setLayout(null);
        oJFrame.add(oJTextFieldNome);

        JLabel oJLabelDataNasc = new JLabel("Dt Nasc:");
        oJLabelDataNasc.setBounds(10, 75, 60, 25);
        oJLabelDataNasc.setHorizontalAlignment(JLabel.RIGHT);
        oJFrame.add(oJLabelDataNasc);

        final JFormattedTextField oJTextFieldDataNasc = new JFormattedTextField(mascara("##/##/####"));

        oJTextFieldDataNasc.setBounds(75, 75, 120, 25);
        oJTextFieldDataNasc.setLayout(null);
        oJFrame.add(oJTextFieldDataNasc);

        JLabel oJLabelSexo = new JLabel("Sexo:");
        oJLabelSexo.setBounds(300, 75, 40, 25);
        oJLabelSexo.setHorizontalAlignment(JLabel.RIGHT);
        oJFrame.add(oJLabelSexo);

        final JComboBox<String> oJComboBox = new JComboBox<>();
        oJComboBox.setBounds(353, 75, 120, 25);
        oJComboBox.addItem("-Selecione-");
        oJComboBox.addItem("Masculino");
        oJComboBox.addItem("Feminino");
        oJFrame.add(oJComboBox);

        JButton oJButtonSalvar = new JButton("Salvar");
        oJButtonSalvar.setBounds(60, 180, 100, 30);
        oJFrame.add(oJButtonSalvar);

        JButton oJButtonAlterar = new JButton("Alterar");
        oJButtonAlterar.setBounds(165, 180, 100, 30);
        oJButtonAlterar.setEnabled(false);
        oJFrame.add(oJButtonAlterar);

        JButton oJButtonExcluir = new JButton("Excluir");
        oJButtonExcluir.setBounds(270, 180, 100, 30);
        oJButtonExcluir.setEnabled(false);
        oJFrame.add(oJButtonExcluir);

        JButton oJButtonGerarArquivo = new JButton("Ger. Arquivo");
        oJButtonGerarArquivo.setBounds(375, 180, 140, 30);
        oJFrame.add(oJButtonGerarArquivo);

        String[] colunas = new String[]{"CPF", "Nome", "Dt Nasc", "Sexo"};

        ArrayList<EPessoa> oListaPessoa = new ArrayList<>();
        PPessoa oPPessoa = new PPessoa();

        try {
            oListaPessoa = oPPessoa.consultarPessoa();
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }
        String linhas[][] = new String[oListaPessoa.size()][4];

        int i = 0;
        for (EPessoa oEPessoa : oListaPessoa) {

            linhas[i][0] = oEPessoa.getCPF();
            linhas[i][1] = oEPessoa.getNome();
            linhas[i][2] = oEPessoa.getDataNasc();
            linhas[i][3] = oEPessoa.getSexo();
            i++;
        }

        final JTextField oJTextFieldFiltroNome = new JTextField();
        oJTextFieldFiltroNome.setBounds(20, 230, 300, 25);
        oJTextFieldFiltroNome.setLayout(null);
        oJFrame.add(oJTextFieldFiltroNome);

        JButton oJButtonFiltro = new JButton("Filtrar");
        oJButtonFiltro.setBounds(330, 230, 80, 25);
        oJFrame.add(oJButtonFiltro);

        JButton oJButtonCancelar = new JButton("Cancelar");
        oJButtonCancelar.setBounds(420, 230, 100, 25);
        oJFrame.add(oJButtonCancelar);

        JTable tabela = new JTable(linhas, colunas);
        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(20, 270, 510, 120);
        scroll.setViewportView(tabela);
        oJFrame.add(scroll);

        oJButtonSalvar.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (oJTextFieldCPF.getText().trim().equalsIgnoreCase("")
                        || oJTextFieldNome.getText().trim().equalsIgnoreCase("")
                        || oJTextFieldDataNasc.getText().trim().equalsIgnoreCase("")
                        || oJComboBox.getSelectedIndex() == 0) {

                    JOptionPane.showMessageDialog(null, "Preencha todos os dados!");

                    return;

                }

                PPessoa oPPessoa = new PPessoa();
                String inclusao = oPPessoa.incluirPessoa(oJTextFieldCPF.getText(), oJTextFieldNome.getText(),
                        oJTextFieldDataNasc.getText(), oJComboBox.getSelectedItem().toString());

                JOptionPane.showMessageDialog(null, inclusao);

                oJFrame.dispose();
                try {
                    Tela.montarTela();
                } catch (IOException e1) {
                    System.out.println(e1.getMessage());
                }

            }
        });

        tabela.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                oJTextFieldCPF.setText(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
                oJTextFieldNome.setText(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
                oJTextFieldDataNasc.setText(tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
                if (tabela.getValueAt(tabela.getSelectedRow(), 3).toString().trim().equalsIgnoreCase("Masculino")) {
                    oJComboBox.setSelectedIndex(1);
                } else {
                    oJComboBox.setSelectedIndex(2);
                }

                oJButtonAlterar.setEnabled(true);
                oJButtonExcluir.setEnabled(true);

                oJButtonExcluir.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        
                        PPessoa oPPessoa = new PPessoa();

                        try {
                            if (!oJTextFieldCPF.getText().equalsIgnoreCase("") && oJTextFieldCPF.getText() != null) {
                                String exclusao = oPPessoa.excluirPessoa(oJTextFieldCPF.getText());
                                JOptionPane.showMessageDialog(null, exclusao);
                                oJFrame.dispose();
                                Tela.montarTela();

                            } else {
                                JOptionPane.showMessageDialog(null, "Selecione uma pessoa!");
                                
                            }

                        } catch (SQLException | IOException e1) {
                            System.out.println(e1.getMessage());
                        }

                    }
                });

                oJButtonAlterar.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        PPessoa oPPessoa = new PPessoa();

                        try {

                            if (oJTextFieldCPF.getText().trim().equalsIgnoreCase("")
                                    || oJTextFieldNome.getText().trim().equalsIgnoreCase("")
                                    || oJTextFieldDataNasc.getText().trim().equalsIgnoreCase("")
                                    || oJComboBox.getSelectedIndex() == 0) {

                                JOptionPane.showMessageDialog(null, "Preencha todos os dados!");

                                return;

                            }

                            if (!oJTextFieldCPF.getText().equalsIgnoreCase("") && oJTextFieldCPF.getText() != null) {
                                String alteracao = oPPessoa.alterarPessoa(oJTextFieldCPF.getText(), oJTextFieldNome.getText(),
                                        oJTextFieldDataNasc.getText(), oJComboBox.getSelectedItem().toString());
                                JOptionPane.showMessageDialog(null, alteracao);
                                oJFrame.dispose();
                                Tela.montarTela();

                            } else {
                                JOptionPane.showMessageDialog(null, "Selecione uma pessoa!");
                            }

                        } catch (IOException e1) {
                            System.out.println(e1.getMessage());
                        }

                    }
                });

            }
        });

        oJButtonFiltro.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                ArrayList<EPessoa> oListaPessoa = new ArrayList<>();
                PPessoa oPPessoa = new PPessoa();

                try {
                    oListaPessoa = oPPessoa.consultarPessoaPorNome(oJTextFieldFiltroNome.getText().trim());
                } catch (SQLException e1) {
                   System.out.println(e1.getMessage());
                }
                String linhas[][] = new String[oListaPessoa.size()][4];

                int i = 0;
                for (EPessoa oEPessoa : oListaPessoa) {

                    linhas[i][0] = oEPessoa.getCPF();
                    linhas[i][1] = oEPessoa.getNome();
                    linhas[i][2] = oEPessoa.getDataNasc();
                    linhas[i][3] = oEPessoa.getSexo();
                    i++;
                }

                oJFrame.remove(scroll);

                JTable tabela = new JTable(linhas, colunas);
                JScrollPane scroll = new JScrollPane();
                scroll.setBounds(20, 270, 510, 120);
                scroll.setViewportView(tabela);
                oJFrame.add(scroll);

            }
        });

        oJButtonGerarArquivo.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                ArrayList<EPessoa> oListaPessoa = new ArrayList<>();
                PPessoa oPPessoa = new PPessoa();

                try {
                    oListaPessoa = oPPessoa.consultarPessoaPorNome(oJTextFieldFiltroNome.getText().trim());

                    try {
                        Arquivo.gerarArquivoTabela("/home/andre/Documentos/Gerado.txt", oListaPessoa);
                        JOptionPane.showMessageDialog(null, "Arquivo gerado com sucesso!");
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        System.out.println(e1.getMessage());
                    }

                } catch (SQLException e1) {
                    System.out.println(e1.getMessage());
                }

            }
        });

        oJButtonCancelar.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                try {
                    oJFrame.dispose();
                    montarTela();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    System.out.println(e1.getMessage());
                }

            }
        });

        oJFrame.setVisible(true);

    }

    public static MaskFormatter mascara(String mascara) {

        MaskFormatter F_Mascara = new MaskFormatter();
        try {
            
            F_Mascara.setMask(mascara); //Atribui a mascara
            F_Mascara.setPlaceholderCharacter(' '); //Caracter para preencimento 
        } catch (ParseException excecao) {
            System.out.println(excecao.getMessage());
        }
        return F_Mascara;
    }

}
