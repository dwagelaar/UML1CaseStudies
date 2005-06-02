/*
 * Created on 1 juin 2004
 *
 */
package org.atl.commandline.engine;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import org.mda.asm.ASM;
import org.mda.asm.ASMExecEnv;
import org.mda.asm.ASMInterpreter;
import org.mda.asm.ASMXMLReader;
import org.mda.asm.Debugger;
import org.mda.asm.NetworkDebugger;
import org.mda.asm.SimpleDebugger;
import org.mda.asm.nativeimpl.ASMModel;
import org.mda.asm.nativeimpl.ASMModule;

/**
 * @author JOUAULT
 *
 */
public class AtlLauncher {
	
	private static AtlLauncher defaultLauncher = null;
	
	public static AtlLauncher getDefault() {
		if(defaultLauncher == null) {
			defaultLauncher = new AtlLauncher();
		}
		return defaultLauncher;
	}
	
	private AtlLauncher() {
		
	}
	
	public Object launch(URL asmurl, Map models, Map asmParams) {
		return launch(asmurl, Collections.EMPTY_MAP, models, asmParams);
	}
	
	public Object launch(URL asmurl, Map libraries, Map models, Map asmParams) {
		return launch(asmurl, libraries, models, asmParams, false);
	}
	
	public Object launch(URL asmurl, Map libraries, Map models, Map asmParams, boolean step) {
		return launch(asmurl, libraries, models, asmParams, new SimpleDebugger(
				/* step = */ step,
				/* stepops = */ new ArrayList(),
				/* deepstepops = */ new ArrayList(),
				/* nostepops = */ new ArrayList(),
				/* deepnostepops = */ new ArrayList(),
				/* showStackTrace = */ true
		));
	}
	
	public Object debug(URL asmurl, Map libraries, Map models, Map asmParams) {
		return launch(asmurl, libraries, models, asmParams, new NetworkDebugger(6060, true));
	}
	
	public Object launch(URL asmurl, Map libraries, Map models, Map asmParams, Debugger debugger) {
		Object ret = null;
		try {
			ASM asm = new ASMXMLReader().read(new BufferedInputStream(asmurl.openStream()));
			ASMModule asmModule = new ASMModule(asm);

			ASMExecEnv env = new ASMExecEnv(asmModule, debugger);
			
			for(Iterator i = models.keySet().iterator() ; i.hasNext() ; ) {
				String mname = (String)i.next();
				env.addModel(mname, (ASMModel)models.get(mname));
			}

			env.registerOperations(asm);
			
			for(Iterator i = libraries.keySet().iterator() ; i.hasNext() ; ) {
				String lname = (String)i.next();
				URL url = (URL)libraries.get(lname);
				ASM lib = new ASMXMLReader().read(new BufferedInputStream(url.openStream()));
				env.registerOperations(lib);
			}

			ASMInterpreter ai = new ASMInterpreter(asm, asmModule, env, asmParams);
			ret = ai.getReturnValue();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ret;
	}
}
