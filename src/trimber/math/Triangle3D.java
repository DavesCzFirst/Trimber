package trimber.math;

public class Triangle3D {
        public Vector3D[] cor = new Vector3D[3];

        public Triangle3D(Vector3D a, Vector3D b, Vector3D c) {
                this.cor[0] = a;
                this.cor[1] = b;
                this.cor[2] = c;
        }

        public Triangle3D() {
                this.cor = new Vector3D[3];
        }
}
