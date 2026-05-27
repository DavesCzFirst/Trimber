package trimber.math;

public class Mat4 {


    public float[][] m;

    public Mat4() {
        m = new float[4][4];
        makeIdentity();
    }

    public void makeIdentity(){
        m[0][0] = 1; m[0][1] = 0; m[0][2] = 0; m[0][3] = 0;
        m[1][0] = 0; m[1][1] = 1; m[1][2] = 0; m[1][3] = 0;
        m[2][0] = 0; m[2][1] = 0; m[2][2] = 1; m[2][3] = 0;
        m[3][0] = 0; m[3][1] = 0; m[3][2] = 0; m[3][3] = 1;
    }

    public Mat4 multiplyMat(Mat4 n){
        Mat4 result = new Mat4();
        for(int x = 0; x< 4; x++){
            for (int y =0; y< 4; y++){
                result.m[x][y] = (m[x][0]*n.m[0][y]) +(m[x][1]*n.m[1][y])+(m[x][2]*n.m[2][y])+(m[x][3]*n.m[3][y]);
            }
        }
        return result;
    }

    public Vector3D multiplyVec3D(Vector3D input) {
        Vector3D result = new Vector3D();
        result.x = (input.x * m[0][0]) + (input.y * m[1][0]) + (input.z * m[2][0]) + (1 * m[3][0]);
        result.y = (input.x * m[0][1]) + (input.y * m[1][1]) + (input.z * m[2][1]) + (1 * m[3][1]);
        result.z = (input.x * m[0][2]) + (input.y * m[1][2]) + (input.z * m[2][2]) + (1 * m[3][2]);
        float w = (input.x * m[0][3]) + (input.y * m[1][3]) + (input.z * m[2][3]) + (1 * m[3][3]);
        if( w != 0.0f) {
            result.x = result.x / w;
            result.y = result.y / w;
            result.z = result.z / w;
        }

        result.u = input.u;
        result.v = input.v;

        return result;
    }

    public static Mat4 rotationZ(float angle){
        Mat4 matrix = new Mat4();
        matrix.m[0][0] = (float) Math.cos(angle);
        matrix.m[0][1] = (float) Math.sin(angle);
        matrix.m[1][0] = (float) -Math.sin(angle);
        matrix.m[1][1] = (float) Math.cos(angle);
        matrix.m[2][2] = 1.0f;
        matrix.m[3][3] = 1.0f;
        return matrix;
    }

    public static Mat4 rotationY(float angleRad) {
        Mat4 matrix = new Mat4();
        matrix.m[0][0] = (float) Math.cos(angleRad);
        matrix.m[0][2] = (float) Math.sin(angleRad);
        matrix.m[1][1] = 1.0f;
        matrix.m[2][0] = (float) -Math.sin(angleRad);
        matrix.m[2][2] = (float) Math.cos(angleRad);
        matrix.m[3][3] = 1.0f;
        return matrix;
    }

    public static Mat4 rotationX(float angle){
        Mat4 matrix = new Mat4();
        matrix.m[0][0] = 1.0f;
        matrix.m[1][1] = (float) Math.cos(angle);
        matrix.m[1][2] = (float) Math.sin(angle);
        matrix.m[2][1] = (float) -Math.sin(angle);
        matrix.m[2][2] = (float) Math.cos(angle);
        matrix.m[3][3] = 1.0f;
        return matrix;
    }

    public static Mat4 makeProjectionMatrix(float fovDegrees, float aspectRatio, float zNear, float zFar){
        Mat4 mat = new Mat4();
        float fovRad = (float) (1.0 / Math.tan(fovDegrees * 0.5 / 180.0 * Math.PI));
        mat.m[0][0] = fovRad / aspectRatio;
        mat.m[1][1] = fovRad;
        mat.m[2][2] = zFar / (zFar - zNear);
        mat.m[3][2] = (-zFar * zNear) / (zFar - zNear);
        mat.m[2][3] = 1.0f;
        mat.m[3][3] = 0.0f;

        return mat;
    }

    public static Mat4 makePointAt(Vector3D pos, Vector3D target, Vector3D up) {
        // 1. Calculate the new Forward direction
        Vector3D newForward = target.subtract(pos);
        newForward.normalize();

        // 2. Calculate the new Up direction
        // We take the current "Up", find out how much it leans forward, and subtract it
        Vector3D a = newForward.multiply(up.dotProduct(newForward));
        Vector3D newUp = up.subtract(a);
        newUp.normalize();

        // 3. Calculate the new Right direction
        // Cross product magically gives us the vector perpendicular to Up and Forward!
        Vector3D newRight = newUp.crossProduct(newForward);

        // 4. Construct the Matrix!
        Mat4 matrix = new Mat4();
        matrix.m[0][0] = newRight.x;	matrix.m[0][1] = newRight.y;	matrix.m[0][2] = newRight.z;	matrix.m[0][3] = 0.0f;
        matrix.m[1][0] = newUp.x;		matrix.m[1][1] = newUp.y;		matrix.m[1][2] = newUp.z;		matrix.m[1][3] = 0.0f;
        matrix.m[2][0] = newForward.x;	matrix.m[2][1] = newForward.y;	matrix.m[2][2] = newForward.z;	matrix.m[2][3] = 0.0f;
        matrix.m[3][0] = pos.x;			matrix.m[3][1] = pos.y;			matrix.m[3][2] = pos.z;			matrix.m[3][3] = 1.0f;
        return matrix;
    }

    public static Mat4 makeQuickInverse(Mat4 m) {
        Mat4 matrix = new Mat4();
        // Transpose the rotation part
        matrix.m[0][0] = m.m[0][0]; matrix.m[0][1] = m.m[1][0]; matrix.m[0][2] = m.m[2][0]; matrix.m[0][3] = 0.0f;
        matrix.m[1][0] = m.m[0][1]; matrix.m[1][1] = m.m[1][1]; matrix.m[1][2] = m.m[2][1]; matrix.m[1][3] = 0.0f;
        matrix.m[2][0] = m.m[0][2]; matrix.m[2][1] = m.m[1][2]; matrix.m[2][2] = m.m[2][2]; matrix.m[2][3] = 0.0f;

        // Invert the translation part
        matrix.m[3][0] = -(m.m[3][0] * matrix.m[0][0] + m.m[3][1] * matrix.m[1][0] + m.m[3][2] * matrix.m[2][0]);
        matrix.m[3][1] = -(m.m[3][0] * matrix.m[0][1] + m.m[3][1] * matrix.m[1][1] + m.m[3][2] * matrix.m[2][1]);
        matrix.m[3][2] = -(m.m[3][0] * matrix.m[0][2] + m.m[3][1] * matrix.m[1][2] + m.m[3][2] * matrix.m[2][2]);
        matrix.m[3][3] = 1.0f;
        return matrix;
    }

    public static Mat4 makeTranslation(float x, float y, float z) {
        Mat4 matrix = new Mat4();
        matrix.m[0][0] = 1.0f;
        matrix.m[1][1] = 1.0f;
        matrix.m[2][2] = 1.0f;
        matrix.m[3][3] = 1.0f;
        matrix.m[3][0] = x;
        matrix.m[3][1] = y;
        matrix.m[3][2] = z;
        return matrix;
    }

    public static Mat4 makeScale(float s) {
        Mat4 matrix = new Mat4();
        matrix.m[0][0] = s;
        matrix.m[1][1] = s;
        matrix.m[2][2] = s;
        matrix.m[3][3] = 1.0f;
        return matrix;
    }


}
