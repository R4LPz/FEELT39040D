import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TreeGUI extends JFrame {

    private JTextField insertTextField;
    private JTextField removeTextField;
    private JTextArea printTextField;
    private JTextField searchTextField;

    private JButton confirmButton;
    private JButton deepSearchButton;
    private JButton breadthSearchButton;
    private JButton viewGraphButton;

    private Tree tree = new Tree();


    //FORMATAR
    private void createForm(){
        setLayout(new BorderLayout());

        /*
        * Painel de Cabeçalho
        * */
        JPanel panelTitle = new JPanel();
        panelTitle.setLayout(new FlowLayout());
        JLabel title =  new JLabel("Árvores Binárias");
        title.setFont(new Font("Verdana", Font.PLAIN, 16));
        panelTitle.add(title);

        /*
        * Painel de Formulário
        * */

        JPanel panelForm = new JPanel();
        panelForm.setLayout(new GridLayout(4,2, 10,10));
        panelForm.setBorder(new EmptyBorder(40,30,80,40));

        JLabel insertLabel = new JLabel("Inserir");
        insertTextField = new JTextField(40);

        JLabel removeLabel = new JLabel("Remover");
        removeTextField = new JTextField(40);

        JLabel searchLabel = new JLabel("Buscar");
        searchTextField = new JTextField(40);

        JLabel printLabel = new JLabel("Resposta");
        printTextField = new JTextArea(3,40);
        printTextField.setEnabled(false);
        printTextField.setDisabledTextColor(Color.BLACK);

        panelForm.add(insertLabel);
        panelForm.add(insertTextField);
        panelForm.add(removeLabel);
        panelForm.add(removeTextField);
        panelForm.add(searchLabel);
        panelForm.add(searchTextField);
        panelForm.add(printLabel);
        panelForm.add(printTextField);


        /*
        * Painel de Botões
        * */
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(2,4, 10,10));
        panelButtons.setBorder(new EmptyBorder(0,30,10,30));

        ConfirmButtonListener confirmButtonListener = new ConfirmButtonListener();
        DeepSearchButtonListener deepSearchButtonListener = new DeepSearchButtonListener();
        BreadthSearchButtonListener breadthSearchButtonListener = new BreadthSearchButtonListener();
        ViewGraphButtonListener viewGraphButtonListener = new ViewGraphButtonListener();


        confirmButton = new JButton("Inserir/Remover");
        confirmButton.addActionListener(confirmButtonListener);

        deepSearchButton = new JButton("Busca Profundidade");
        deepSearchButton.addActionListener(deepSearchButtonListener);

        breadthSearchButton = new JButton("Busca Largura");
        breadthSearchButton.addActionListener(breadthSearchButtonListener);

        viewGraphButton = new JButton("Ver Gráfico");
        viewGraphButton.addActionListener(viewGraphButtonListener);

        panelButtons.add(confirmButton);
        panelButtons.add(viewGraphButton);
        panelButtons.add(deepSearchButton);
        panelButtons.add(breadthSearchButton);


        add(panelTitle,BorderLayout.NORTH);
        add(panelForm, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);
    }

    private class ConfirmButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                if(!insertTextField.getText().equals("")){
                    int insertInputValue = Integer.parseInt(insertTextField.getText());
                    tree.insert(insertInputValue);
                }
                if(!removeTextField.getText().equals("")){
                    int removeInputValue = Integer.parseInt(removeTextField.getText());
                    tree.remove(removeInputValue);
                }
                insertTextField.setText("");
                removeTextField.setText("");
                searchTextField.setText("");
                printTextField.setText(tree.printTree());
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Digite apenas números!");
            }
        }
    }

    private class DeepSearchButtonListener implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            try {
                if(!tree.isEmpty()){
                    int searchValue = Integer.parseInt(searchTextField.getText());
                    printTextField.setText("Ordem de busca: \n" + tree.deepSearch(searchValue));
                }
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Digite um valor para ser buscado");
            }


        }
    }

    private class BreadthSearchButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                if(!tree.isEmpty()){
                    int searchValue = Integer.parseInt(searchTextField.getText());
                    printTextField.setText("Ordem de busca: \n" + tree.breadthSearch(searchValue));
                }
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Digite um valor para ser buscado");
            }

        }
    }


    private class ViewGraphButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(!tree.isEmpty()){
                Graph graph = new Graph(tree);
                JFrame j1 = new JFrame();
                j1.setVisible(true);
                j1.add(graph);
                j1.setSize(530, 400);
            }
        }
    }

    public TreeGUI(){
        super("Árvores Binárias");
        createForm();


    }
}
