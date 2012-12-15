#pragma version(1)
#pragma rs java_package_name(com.MyLibrary.Renderscript.Kernel)

const uchar4 *bitmap;
uchar4 *outBitmap;
rs_matrix3x3 kernel;

static int imageWidth, imageHeight;
static uint imageEdgeX, imageEdgeY;

rs_allocation inAlloc, outAlloc;
rs_script mScript;

void initScript(){
	imageWidth = rsAllocationGetDimX(inAlloc);
	//imageHeight = rsAllocationGetDimY(inAlloc);
	imageEdgeX = imageWidth - 1;
	imageEdgeY = imageHeight - 1;
	
	rsForEach(mScript, inAlloc, outAlloc, NULL);
}

void root(const uchar4 *in, uchar4 *out, const void* usrData, uint32_t x, uint32_t y){
	
	if( (x >=1 && y >= 1) && (x <= imageEdgeX && y <= imageEdgeY)){
		int yComponent = imageWidth * y;
		
		float3 subSRC;
		
		for(int i = -1; i <= 1; i++){
			for(int j = 0; j < 3; j++){
				subSRC = rsUnpackColor8888(bitmap[(x-1+j) + (yComponent + (imageWidth*i))]).rgb;
				float3 original = subSRC;
				
				subSRC = rsMatrixMultiply(&kernel, subSRC);	
				
				// TODO : Do additional computations HERE
				
				subSRC = clamp(subSRC, 0.f, 255.f);
				outBitmap[(x-1+j) + (yComponent + (imageWidth*i))] = rsPackColorTo8888(subSRC);
			}
		}
		
		*out = *in;
	}
	
}