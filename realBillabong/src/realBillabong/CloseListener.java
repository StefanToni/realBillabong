package realBillabong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CloseListener implements ActionListener{
    public CloseListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
    public void actionPerformed(ActionEvent e) {
        //DO SOMETHING
		System.out.println("GG");
        System.exit(0);
    }
}