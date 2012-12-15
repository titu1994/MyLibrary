package com.MyLibrary.Renderscript.Kernel;

import android.renderscript.Allocation;
import android.renderscript.Matrix3f;
import android.renderscript.RenderScript;

public class RenderScriptKernelProcessor {
	
	private RenderScript mRS;
	private ScriptC_ProcessingScript mScript;
	private Allocation inAlloc, outAlloc, outImageAlloc;
	
	private Matrix3f kernel = new Matrix3f();
	
	private boolean initKernelBoolean = false;
	
	protected RenderScriptKernelProcessor(RenderScript rS, ScriptC_ProcessingScript pScript, Allocation iAlloc){
		
		mRS = rS;
		mScript = pScript;
		inAlloc = iAlloc;
		
		outAlloc = Allocation.createTyped(mRS, inAlloc.getType());
		outImageAlloc = Allocation.createTyped(mRS, inAlloc.getType());
		
        mScript.set_mScript(mScript);
	}
	
	/**
	 * Initialises the kernel which will be used in the script. Should be run to set up a custom kernel
	 * or it will resort to a default Identity matrix that does nothing to your image. 
	 * 
	 * Default mode runs as a last resort if initKernel(..) has not been used before the initScript() method is used
	 * 
	 * @param kernelArr = A two dimensional array consisting of multipliers to the source image pixel values.
	 */
	protected void initKernel(int[][] kernelArr){
		
		if(kernelArr != null){
			for(int i = 0; i < 3; i++){
	    		for(int j = 0;j < 3; j++){
	    			kernel.set(i, j, kernelArr[i][j]);
	    		}
	    	}
		}
		else{
			final int[][] defaultKernelArr = {{1,0,0},{0,1,0},{0,0,1}};
			
			for(int i = 0; i < 3; i++){
	    		for(int j = 0;j < 3; j++){
	    			kernel.set(i, j, defaultKernelArr[i][j]); //Defaults to a {{1,0,0},{0,1,0},{0,0,1}} Matrix Kernel.
	    		}
	    	}
		}
		
    	mScript.set_kernel(kernel);
    	
    	initKernelBoolean = true;
	}
	
	/**
	 * Initialises the script. 
	 */
	protected void initScript(){
		
        mScript.bind_bitmap(inAlloc);
        mScript.bind_outBitmap(outImageAlloc);
        
        mScript.set_inAlloc(inAlloc);
        mScript.set_outAlloc(outAlloc);
        
        if(initKernelBoolean)
        	mScript.invoke_initScript();
        else{
        	initKernel(null);
        	mScript.invoke_initScript();
        }
	}
	
	/**
	 * Swaps the input with an output Allocation to allow for running multiple instances of the script. 
	 * Useful for sharpening filters and UnSharpMasks. If a null value is sent as the parameter, then it does absolutely nothing.
	 * 
	 * @param outImageAlloc = The allocation recieved from the getOutImageAlloc() method which is valid only after running
	 * 						  the script once. It is the Allocation of the modified image which is generated via the script.
	 */
	protected void swapAllocs(Allocation outImageAlloc){
		
		if(outImageAlloc != null){
			inAlloc = outImageAlloc;
			outAlloc = Allocation.createTyped(mRS, inAlloc.getType());
			this.outImageAlloc = Allocation.createTyped(mRS, inAlloc.getType());
		}
	}
	
	/**
	 * Convenience method which is absolutely worthless. Just incase you lose the instance of you input Allocation and 
	 * require it again. The returned value is exactly the input Allocation.
	 * 
	 * @return An Allocation of the original Image sent in. Absolutely worthless as outAlloc will always be inAlloc according to the script.
	 */
	protected Allocation getOutAlloc(){
		if(outAlloc != null){
			return outAlloc;
		}
		else{
			return inAlloc;
		}
	}
	
	/**
	 * Convenience method which is used to obtain the Allocation of the image modified by the script. 
	 * Used to obtain the resultant Allocation of the bitmap image.
	 * 
	 * @return The Allocation of the image modified by the script. Used for running multiple instances of the script 
	 * 		   or for utilising in an imageview after running the script. 
	 */
	protected Allocation getOutImageAlloc(){
		if(outImageAlloc != null)
			return outImageAlloc;
		else
			return inAlloc;
	}
	
	/**
	 * Should be used to release memory. Eventually the GC will do it for you but you may as well get it over with now ..
	 */
	protected void destroyRenderScriptInstances(){
		mRS.destroy();
		mScript.destroy();
		inAlloc.destroy();
		outAlloc.destroy();
		outImageAlloc.destroy();
		
		mRS = null;
		mScript = null;
		inAlloc = null;
		outAlloc = null;
		outImageAlloc = null;
	}
	
}
