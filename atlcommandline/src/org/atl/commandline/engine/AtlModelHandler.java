/*
 * Created on 1 juin 2004
 *
 */
package org.atl.commandline.engine;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.atl.engine.vm.nativelib.ASMModel;

/**
 * @author JOUAULT
 * @author Dennis Wagelaar
 */
public abstract class AtlModelHandler {
	
	public final static String AMH_MDR = "MDR";
	public final static String AMH_EMF = "EMF";

	private static final String modelHandlers[] = new String[] {AMH_MDR, AMH_EMF};
	
	private static Map defaultModelHandlers = new HashMap();
		
	public static AtlModelHandler getDefault(String repository) throws Exception {
		AtlModelHandler ret = (AtlModelHandler)defaultModelHandlers.get(repository);
		if(ret == null) {
			if(AMH_MDR.equals(repository)) {
				ret = new AtlMDRModelHandler();
				defaultModelHandlers.put(AMH_MDR, ret);
            } else if(AMH_EMF.equals(repository)) {
                ret = new AtlEMFModelHandler();
                defaultModelHandlers.put(AMH_EMF, ret);             
            }
		}
		
		return ret;
	}
	
	public static String[] getModelHandlers() {
		return modelHandlers;
	}
	
	public abstract void saveModel(final ASMModel model) throws IOException;

	public abstract void saveModel(final ASMModel model, String fileName)
            throws IOException;

	public abstract ASMModel getAtl();

	public abstract ASMModel getMof();
	
	public abstract ASMModel loadModel(String name, ASMModel metamodel, InputStream in)
            throws Exception;
	
	public abstract ASMModel newModel(String name, ASMModel metamodel) throws Exception;
	
	public abstract ASMModel getBuiltInMetaModel(String name);
}
