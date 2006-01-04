/*
 * Created on 2-jun-2005
 * (C) 2005, Dennis Wagelaar, SSEL, VUB
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License, version 2 as
 *  published by the Free Software Foundation.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *
 *  (See the file "COPYING" that is included with this source distribution.)
 */
package org.atl.commandline;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import org.atl.commandline.engine.AtlLauncher;
import org.atl.commandline.engine.AtlModelHandler;
import org.atl.engine.vm.nativelib.ASMModel;

/**
 * Command-line interface to the ATLAS transformation engine.
 * @author Dennis Wagelaar
 */
public class Main implements Runnable {
    public static final String USAGE =
        "Usage: <this program> " +
        "--trans <transformation url> " +
        "[--in <id>=<model> <id>=<metamodel> <MDR|EMF>] " +
        "[--out <id>=<model> <id>=<metamodel> <MDR|EMF>] " +
        "[--lib <id>=<library url>] " +
        "--next --trans ...";
    
    public URL trans = null;
    public Map in = new HashMap();
    public HashMap out = new HashMap();
    public HashMap libs = new HashMap();
    public HashMap paths = new HashMap();
    public HashMap modelCache = new HashMap();
    public HashMap handlers = new HashMap();
    
    private int argPos = 0;
    
    public static void main(String[] args) {
        int argsLeft = args.length;
        Main main = new Main();
        do {
            argsLeft = main.parseArgs(args);
            if (argsLeft > -1)
                main.run();
        } while (argsLeft > 0);
        if (argsLeft < 0) {
            System.exit(1);
        } else {
            System.exit(0);
        }
    }
    
    /**
     * Parses the command line arguments
     * @param args
     * @return number of arguments left (e.g. if "--next" is used) or -1 if error
     */
    public int parseArgs(String[] args) {
        if (args.length <= argPos+1) {
            System.out.println(USAGE);
            return -1;
        }
        try {
            for (int i = argPos; i < args.length; i++) {
                System.out.println(args[i]);
                if (args[i].equals("--trans")) {
                    i++;
                    trans = new URL(args[i]);
                } else if (args[i].equals("--in")) {
                    i++; i++; i++;
                    addInputModel(args[i-2], args[i-1], args[i]);
                } else if (args[i].equals("--out")) {
                    i++; i++; i++;
                    addOutputModel(args[i-2], args[i-1], args[i]);
                } else if (args[i].equals("--lib")) {
                    i++;
                    addLib(args[i]);
                } else if (args[i].equals("--next")) {
                    i++;
                    argPos = i;
                    return args.length - argPos;
                } else {
                    System.out.print(USAGE);
                    return -1;
                }
            }
        } catch (Exception e) {
            System.err.print(e.toString());
            e.printStackTrace();
            System.out.print(USAGE);
            return -1;
        }
        return 0;
    }

    /**
     * Parses --in arguments and adds them to the internal list
     * @param model
     * @param metamodel
     * @param repository
     * @throws Exception
     */
    public void addInputModel(String model, String metamodel, String repository)
            throws Exception {
        StringTokenizer mdl = new StringTokenizer(model, "=");
        String modelid = mdl.nextToken();
        String modelpath = mdl.nextToken();
        StringTokenizer metamdl = new StringTokenizer(metamodel, "=");
        String metaid = metamdl.nextToken();
        String metapath = metamdl.nextToken();

        AtlModelHandler amh = AtlModelHandler.getDefault(repository);
        ASMModel metaModel;
        ASMModel inputModel;
        if (metaid.equals("MOF")) {
            System.out.println("Input metamodel is MOF - using built-in metamodel");
            metaModel = amh.getMof();
        }
        else {
            metaModel = (ASMModel) in.get(metaid);
            if (metaModel == null || !metapath.equals(paths.get(metaid))) {
                System.out.println("Input metamodel " + metaid + " @ " + amh + " not yet loaded - loading from " + metapath);
                metaModel = amh.loadModel(metaid, amh.getMof(), new FileInputStream(metapath));
                in.put(metaid, metaModel);
            }
        }
        handlers.put(metaid, repository);
        System.out.println("Using input metamodel " + metaModel);
        inputModel = (ASMModel) modelCache.get(modelid);
        if (inputModel == null || 
                !modelpath.equals(paths.get(modelid)) || 
                !metaid.equals(inputModel.getMetamodel().getName())) {
            System.out.println("Loading input model " + modelid + " from " + modelpath);
            inputModel = amh.loadModel(modelid, metaModel, new FileInputStream(modelpath));
            modelCache.put(modelid, inputModel);
        }
        System.out.println("Using input model " + inputModel);
        in.put(modelid, inputModel);
        paths.put(modelid, modelpath);
        paths.put(metaid, metapath);
    }

    /**
     * Parses --out arguments and adds them to the internal list
     * @param model
     * @param metamodel
     * @param repository
     * @throws Exception
     */
    public void addOutputModel(String model, String metamodel, String repository)
            throws Exception {
        StringTokenizer mdl = new StringTokenizer(model, "=");
        String modelid = mdl.nextToken();
        String modelpath = mdl.nextToken();
        StringTokenizer metamdl = new StringTokenizer(metamodel, "=");
        String metaid = metamdl.nextToken();
        String metapath = metamdl.nextToken();

        AtlModelHandler amh = AtlModelHandler.getDefault(repository);
        ASMModel metaModel;
        ASMModel outputModel;
        if (metaid.equals("MOF")) {
            System.out.println("Output metamodel is MOF - using built-in metamodel");
            metaModel = amh.getMof();
        }
        else {
            metaModel = (ASMModel) in.get(metaid);
            if (metaModel == null || !metapath.equals(paths.get(metaid))) {
                System.out.println("Loading output metamodel " + metaid + " @ " + amh + " from " + metapath);
                metaModel = amh.loadModel(metaid, amh.getMof(), new FileInputStream(metapath));
                in.put(metaid, metaModel);
            }
        }
        handlers.put(metaid, repository);
        System.out.println("Using output metamodel " + metaModel);
        System.out.println("Creating new model " + modelid + " for output");
        outputModel = amh.newModel(modelid, metaModel);
        out.put(modelid, outputModel);
        paths.put(modelid, modelpath);
        paths.put(metaid, metapath);
    }

    /**
     * Parses --lib arguments and adds them to the internal list
     * @param lib
     * @throws Exception
     */
    public void addLib(String lib) throws Exception {
        StringTokenizer l = new StringTokenizer(lib, "=");
        String libid = l.nextToken();
        URL liburl = new URL(l.nextToken());
        libs.put(libid, liburl);
    }
    
    /**
     * Executes the ATL engine
     */
    public void run() {
        try {
            System.out.println("Starting model transformation " + trans);
            Map models = new HashMap();
            // add input models
            for(Iterator i = in.keySet().iterator() ; i.hasNext() ; ) {
                String mName = (String)i.next();
                models.put(mName, in.get(mName));
            }
            // add output models
            for(Iterator i = out.keySet().iterator() ; i.hasNext() ; ) {
                String mName = (String)i.next();
                models.put(mName, out.get(mName));
            }
            Map params = Collections.EMPTY_MAP;
            AtlLauncher myLauncher = AtlLauncher.getDefault();
            myLauncher.launch(trans, libs, models, params);
            System.out.println("Model transformation done");
            // save output models
            for(Iterator i = out.keySet().iterator(); i.hasNext() ; ) {
                String mName = (String)i.next();
                ASMModel currentOutModel = (ASMModel)out.get(mName);
                String mmName = currentOutModel.getMetamodel().getName();
                AtlModelHandler.getDefault((String)handlers.get(mmName)).saveModel(currentOutModel, (String) paths.get(mName));
                System.out.println("Wrote " + (String) paths.get(mName));
                modelCache.put(mName, currentOutModel);
                i.remove();
            }
        } catch (Exception e) {
            System.err.print(e.toString());
            e.printStackTrace();
            System.out.println(USAGE);
            System.exit(1);
        }
    }
}
