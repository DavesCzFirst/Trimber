package trimber.math;

public class Vector3D {
    public float  x;
    public float y;
    public float z;

    public Vector3D(float x, float y, float z) {
        this.z = z;
        this.x = x;
        this.y = y;
    }

    /*public Vector3D(float x, float y, float z) {
        this.z = z;
        this.x = x;
        this.y = y;
    }*/

    public Vector3D() {

    }
    public float length(){
        return (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }



    public Vector3D normalize(){
        float l = length();
        return divide(l);
    }

    public static void add(Vector3D u, Vector3D output){
        float x =u.x;
        float y = u.y;
        float z = u.z;

        output.x += x;
        output.y += y;
        output.z += z;
    }

    public Vector3D add(Vector3D u){
        return  new Vector3D(x+u.x, y+u.y, z + u.z);
    }

    public Vector3D subtract(Vector3D u){
        return  new Vector3D(x-u.x, y-u.y, z - u.z);
    }

    public Vector3D multiply(float n){
        return  new Vector3D(x* n, y * n, z * n);
    }

    public Vector3D divide(float n){
        return  new Vector3D(x/ n, y / n, z / n);
    }

    public float dotProduct(Vector3D u){
        return (float) (x * u.x + y * u.y + z *u.z);
    }

    public Vector3D crossProduct(Vector3D u){
        return new Vector3D(y*u.z-z*u.y, z*u.x-x*u.z, x*u.y-y*u.x);
    }

}
