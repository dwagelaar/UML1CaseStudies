/*
 * Created on 1 juin 2004
 *
 */
package org.atl.commandline.engine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.mda.asm.nativeimpl.ASMMDRModel;
import org.mda.asm.nativeimpl.ASMModel;

/**
 * @author JOUAULT
 * @author Dennis Wagelaar
 */
public class AtlMDRModelHandler extends AtlModelHandler{
	
	private ASMMDRModel atlmm;
	private ASMMDRModel mofmm;	
	public void saveModel(final ASMModel model) throws IOException {
		saveModel(model, model.getName() + ".xmi");
	}
	
	public void saveModel(final ASMModel model, String fileName)
            throws IOException {
		File file = new File(fileName);
		final FileOutputStream out = new FileOutputStream(file);
		((ASMMDRModel) model).save(out);
	}

	public ASMModel getAtl() {
		return atlmm;
	}

	public ASMModel getMof() {
		return mofmm;
	}
	
	public ASMModel loadModel(String name, ASMModel metamodel, InputStream in)
            throws Exception {
		ASMModel ret = null;
		ret = ASMMDRModel.loadASMMDRModel(name, (ASMMDRModel)metamodel, in);
		return ret;
	}
	public ASMModel newModel(String name, ASMModel metamodel) throws Exception {
		ASMModel ret = null;
		ret = ASMMDRModel.newASMMDRModel(name, (ASMMDRModel)metamodel);
		return ret;
	}

	protected AtlMDRModelHandler() throws Exception {
		URL atlurl = AtlMDRModelHandler.class.getResource("resources/ATL-0.2.xmi");
		mofmm = ASMMDRModel.createMOF();
		atlmm = ASMMDRModel.loadASMMDRModel("ATL", mofmm, atlurl);
	}
	
	public ASMModel getBuiltInMetaModel(String name) {
		ASMModel ret = null;
		
		return ret;
	}
}
