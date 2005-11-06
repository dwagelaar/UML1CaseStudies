/*
 * Created on 1 juin 2004
 *
 */
package org.atl.commandline.engine;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.atl.engine.repositories.emf4atl.ASMEMFModel;
import org.atl.engine.vm.nativelib.ASMModel;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * @author JOUAULT
 *
 */
public class AtlEMFModelHandler extends AtlModelHandler {
	private ASMEMFModel mofmm;
	private ASMEMFModel atlmm;
		
	public void saveModel(final ASMModel model) {
		saveModel(model, model.getName() + ".ecore");
	}
	
	public void saveModel(final ASMModel model, String uri) {
		saveModel(model, URI.createURI(uri));
	}
	
	private void saveModel(final ASMModel model, URI uri) {
		Resource r = ((ASMEMFModel)model).getExtent();
		r.setURI(uri);
		try {
			r.save(Collections.EMPTY_MAP);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	protected AtlEMFModelHandler() {
		URL atlurl = AtlEMFModelHandler.class.getResource("resources/ATL-0.2.ecore");
		
		mofmm = org.atl.engine.repositories.emf4atl.ASMEMFModel.createMOF(null);
			
		try {
			atlmm = ASMEMFModel.loadASMEMFModel("ATL", mofmm, atlurl, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ASMModel getMof() {
		return mofmm;
	}

	public ASMModel getAtl() {
		return atlmm;
	}
	
	public ASMModel loadModel(String name, ASMModel metamodel, InputStream in) {
		ASMModel ret = null;
		
		try {
			ret = ASMEMFModel.loadASMEMFModel(name, (ASMEMFModel)metamodel, in, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public ASMModel newModel(String name, ASMModel metamodel) {
		ASMModel ret = null;
		
		try {
			ret = ASMEMFModel.newASMEMFModel(name, (ASMEMFModel)metamodel, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	private Map bimm = new HashMap();
	
	public ASMModel getBuiltInMetaModel(String name) {
		ASMModel ret = (ASMModel)bimm.get(name);

		if(ret == null) {
			URL mmurl = AtlParser.class.getResource("resources/" + name + ".ecore");
			
			try {
				ret = loadModel(name, mofmm, mmurl.openStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			bimm.put(name, ret);
		}

		return ret;
	}

}
