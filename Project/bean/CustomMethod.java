package project.bean;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class CustomMethod {

	public Icon resizeIcon(ImageIcon ii, int w, int h) {
		ImageIcon ic = ii;
		Image img = ic.getImage();
		Image img2 = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		ic = new ImageIcon(img2);
		return ic;
	}
	
	public String toWon(long Amount) {
		StringBuilder sb = new StringBuilder();
<<<<<<< HEAD:Project/bean/CustomMethod.java
=======
		if (Amount == 0) {
			sb.append("0");
		}
>>>>>>> 2e31d6211e926b535d439a23067bc5f0b7a9770d:Project/Bean/CustomMethod.java
		while (true) {
			if (Amount > (long)Math.pow(10, 12)) {
				sb.append((long)Math.floor(Amount/(long)Math.pow(10, 12))+"조");
				Amount %= (long)Math.pow(10, 12);
			} else if (Amount > (long)Math.pow(10, 8)) {
				sb.append((long)Math.floor(Amount/(long)Math.pow(10, 8))+"억");
				Amount %= (long)Math.pow(10, 8);
			} else if (Amount > (long)Math.pow(10, 4)) {
				sb.append((long)Math.floor(Amount/(long)Math.pow(10, 4))+"만");
				Amount %= (long)Math.pow(10, 4);
			} else {
				if (Amount == 0) {
					sb.append("원");
				} else {
					sb.append(Amount+"원");
				}
				break;
			}
		}
		return sb.toString();
	}
	
}
