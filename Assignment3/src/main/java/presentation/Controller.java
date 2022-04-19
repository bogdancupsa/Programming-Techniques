package presentation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    private final StartView startView;
    private final ClientView clientView;
    private final ProductView productView;
    private final OrderView orderView;
    private final ClientBoxView clientBox;
    private final ProductBoxView productBox;

    class Listener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(startView.getClientButton())){
                startView.close();
                clientView.display();
            }else if(e.getSource().equals(startView.getProductsButton())){
                startView.close();
                productView.display();
            }else{
                startView.close();
                orderView.display();
            }
        }
    }

    class BackListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(clientView.getBackButton())) {
                clientView.close();
                startView.display();
            }else{
                productView.close();
                startView.display();
            }
        }
    }

    class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (clientView.getAddButton().equals(source) || clientView.getEditButton().equals(source)) {
                clientView.close();
                clientBox.display();
            } else if (clientView.getDeleteButton().equals(source)) {
                System.out.println("delete A");
            } else if (clientView.getViewButton().equals(source)) {
                System.out.println("view A");
            } else if (productView.getAddButton().equals(source)) {
                System.out.println("add P");
            } else if (productView.getEditButton().equals(source)) {
                System.out.println("edit P");
            } else if (productView.getDeleteButton().equals(source)) {
                System.out.println("delete P");
            } else {
                System.out.println("view P");
            }
        }
    }

    public Controller(StartView start, ProductView product, OrderView order, ClientView client, ClientBoxView cBox, ProductBoxView pBox){
        startView = start;
        productView = product;
        orderView = order;
        clientView = client;
        clientBox = cBox;
        productBox = pBox;
        startView.addActionListeners(new Listener());
        clientView.addActionListeners(new ButtonListener(), new BackListener());
        productView.addActionListeners(new ButtonListener(), new BackListener());
        startView.display();
    }
}
