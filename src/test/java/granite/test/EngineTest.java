package granite.test;

import granite.engine.Engine;
import granite.engine.IGame;
import granite.engine.entities.Camera;
import granite.engine.entities.Entity;
import granite.engine.input.Input;
import granite.engine.model.Model;
import org.joml.Vector3f;
import org.junit.Test;

import java.io.IOException;

import static org.lwjgl.glfw.GLFW.*;

public class EngineTest {

    @Test
    public void runEngine() {

        Engine engine = new Engine(new IGame() {

            Entity entity1;

            @Override
            public void attach(Engine engine) {
////                TexturedMesh vao = new TexturedMesh(new float[]{0f, 0f, 0f, -.5f, 0f, 0f, -.5f, -.5f, 0f, 0f, -.5f, 0f, 0f, 0f, 1f, -.5f, 0f,
////                        1f, -.5f, -.5f, 1f, 0f, -.5f, 1f}, new float[]{0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1,
////                        0, 1, 1, 0, 0, 1}, new int[]{0, 1, 2, 0, 2, 3, 0, 1, 4, 4, 1, 5, 4, 5, 6, 4, 6, 7});
////                Texture texture = new Texture("texture1.png");
////                Model model = new Model(vao, texture);
//                TexturedMesh vao = engine.getModelManager().loadVAO("donut.obj");
//                Model model = new Model(vao, null);
//                entity1 = engine.getEntityManager().addEntity(model, new Vector3f(0, 0, -1), new Vector3f(0, 0, 0), 1);
//                entity2 = engine.getEntityManager().addEntity(model, new Vector3f(0, 1, -1), new Vector3f(0, 1, 0), 1);
                try {
                    Model model = Model.load("cubes.obj", engine.getRenderer().getEntityRenderer());
                    entity1 = new Entity();
                    entity1.addChild(model);
                    engine.getScene().getCamera().move(new Vector3f(0, 0, 10));
                    engine.getScene().addChild(entity1);
                    new Globe(engine);
//                for (int i = 0; i < 100; i++) {
//                    engine.getRenderer().getEntityRenderer().getEntityManager().addEntity(model, new Vector3f(ThreadLocalRandom.current().nextInt(-100, 100), ThreadLocalRandom.current().nextInt(-100, 100), ThreadLocalRandom.current().nextInt(-100, 100)), new Vector3f(0, 0, 0), 1);
//                }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void update(Engine engine) {
                Input input = engine.getInput();
                Camera camera = engine.getScene().getCamera();
                float x = 0, y = 0, z = 0, rx = 0, ry = 0, rz = 0;
                if (input.isPressed(GLFW_KEY_W)) {
                    z = -.2f;
                }
                if (input.isPressed(GLFW_KEY_A)) {
                    x = -.2f;
                }
                if (input.isPressed(GLFW_KEY_S)) {
                    z = .2f;
                }
                if (input.isPressed(GLFW_KEY_D)) {
                    x = .2f;
                }
                if (input.isPressed(GLFW_KEY_UP)) {
                    rx = -.05f;
                }
                if (input.isPressed(GLFW_KEY_DOWN)) {
                    rx = .05f;
                }
                if (input.isPressed(GLFW_KEY_LEFT)) {
                    ry = -.05f;
                }
                if (input.isPressed(GLFW_KEY_RIGHT)) {
                    ry = .05f;
                }
                camera.move(new Vector3f(x, y, z));
                camera.rotate(new Vector3f(rx, ry, rz));
            }

            @Override
            public void destroy() {

            }
        });

        engine.start();
    }
}
