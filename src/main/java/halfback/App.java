package halfback;

import java.io.IOException;
import java.io.File;

import halfback.Utils;
import halfback.cmpm.ChronicleMiningAlgo;
import halfback.cmpm.model.ChronicleBuilder;
import halfback.cmpm.view.MainFrame;
import preprocessing.Preprocessing;

public class App {

	public static void main(String args[]) throws NullPointerException {

		String infile = Utils.getParam(args, "if");
		String outfile = Utils.getParam(args, "of");
		Boolean preprocess = Utils.getBooleanParam(args, "p");
		if (infile == null) {
			throw new NullPointerException();
		} else {
			File file = new File(infile);
			if (!file.exists()) {
				throw new NullPointerException();
			}
			if (!file.isAbsolute()) {
				infile = file.getAbsolutePath();
			}
		}

		if (outfile == null) {
			outfile = System.getProperty("user.dir") + "\\output";
		} else {
			File file = new File(outfile);
			boolean overwrite = Utils.getBooleanParam(args, "f");
			if (file.exists() && !overwrite) {
				throw new NullPointerException();
			} else {
				outfile = file.getAbsolutePath();
			}
		}
		
		if (preprocess) {
			System.out.println("Running preprocessing task...");
			Preprocessing p = new Preprocessing();
			try {
				p.run(infile);
				infile = outfile + "_xd.txt";
				p.toSPMF(infile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Preprocessing ended.");
		} else {
			// TODO : read 
		}

		ChronicleMiningAlgo cm = new ChronicleMiningAlgo(Utils.getDoubleParam(args, "m", 0.9), infile, outfile);
		try {
			System.out.println("Running extraction algorithm.");
			cm.run();
			System.out.println("Extraction ended.");
		} catch (IOException ioe) {
			ioe.printStackTrace(System.err);
		}

		MainFrame mf = new MainFrame(ChronicleBuilder.getChronicles());
	}

}