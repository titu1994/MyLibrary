/*
 * Copyright (C) 2011-2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * This file is auto-generated. DO NOT MODIFY!
 * The source Renderscript file: C:\Users\Somshubra\Documents\GitHub\MyLibrary\MyLibrary\src\com\MyLibrary\Renderscript\Kernel\ProcessingScript.rs
 */
package com.MyLibrary.Renderscript.Kernel;

import android.renderscript.*;
import android.content.res.Resources;

/**
 * @hide
 */
public class ScriptC_ProcessingScript extends ScriptC {
    private static final String __rs_resource_name = "processingscript";
    // Constructor
    public  ScriptC_ProcessingScript(RenderScript rs) {
        this(rs,
             rs.getApplicationContext().getResources(),
             rs.getApplicationContext().getResources().getIdentifier(
                 __rs_resource_name, "raw",
                 rs.getApplicationContext().getPackageName()));
    }

    public  ScriptC_ProcessingScript(RenderScript rs, Resources resources, int id) {
        super(rs, resources, id);
        __U8_4 = Element.U8_4(rs);
    }

    private Element __U8_4;
    private FieldPacker __rs_fp_ALLOCATION;
    private FieldPacker __rs_fp_SCRIPT;
    private final static int mExportVarIdx_bitmap = 0;
    private Allocation mExportVar_bitmap;
    public void bind_bitmap(Allocation v) {
        mExportVar_bitmap = v;
        if (v == null) bindAllocation(null, mExportVarIdx_bitmap);
        else bindAllocation(v, mExportVarIdx_bitmap);
    }

    public Allocation get_bitmap() {
        return mExportVar_bitmap;
    }

    private final static int mExportVarIdx_outBitmap = 1;
    private Allocation mExportVar_outBitmap;
    public void bind_outBitmap(Allocation v) {
        mExportVar_outBitmap = v;
        if (v == null) bindAllocation(null, mExportVarIdx_outBitmap);
        else bindAllocation(v, mExportVarIdx_outBitmap);
    }

    public Allocation get_outBitmap() {
        return mExportVar_outBitmap;
    }

    private final static int mExportVarIdx_kernel = 2;
    private Matrix3f mExportVar_kernel;
    public synchronized void set_kernel(Matrix3f v) {
        mExportVar_kernel = v;
        FieldPacker fp = new FieldPacker(36);
        fp.addMatrix(v);
        setVar(mExportVarIdx_kernel, fp);
    }

    public Matrix3f get_kernel() {
        return mExportVar_kernel;
    }

    private final static int mExportVarIdx_inAlloc = 3;
    private Allocation mExportVar_inAlloc;
    public synchronized void set_inAlloc(Allocation v) {
        setVar(mExportVarIdx_inAlloc, v);
        mExportVar_inAlloc = v;
    }

    public Allocation get_inAlloc() {
        return mExportVar_inAlloc;
    }

    private final static int mExportVarIdx_outAlloc = 4;
    private Allocation mExportVar_outAlloc;
    public synchronized void set_outAlloc(Allocation v) {
        setVar(mExportVarIdx_outAlloc, v);
        mExportVar_outAlloc = v;
    }

    public Allocation get_outAlloc() {
        return mExportVar_outAlloc;
    }

    private final static int mExportVarIdx_mScript = 5;
    private Script mExportVar_mScript;
    public synchronized void set_mScript(Script v) {
        setVar(mExportVarIdx_mScript, v);
        mExportVar_mScript = v;
    }

    public Script get_mScript() {
        return mExportVar_mScript;
    }

    private final static int mExportFuncIdx_initScript = 0;
    public void invoke_initScript() {
        invoke(mExportFuncIdx_initScript);
    }

}

