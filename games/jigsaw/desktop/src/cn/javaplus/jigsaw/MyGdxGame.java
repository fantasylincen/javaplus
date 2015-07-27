//package cn.javaplus.jigsaw;
//import com.badlogic.gdx.ApplicationListener;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.Mesh;
//import com.badlogic.gdx.graphics.VertexAttribute;
//import com.badlogic.gdx.graphics.VertexAttributes;
//import com.badlogic.gdx.graphics.glutils.ShaderProgram;
//
//
//public class MyGdxGame implements ApplicationListener {
//
//   Mesh mesh;
//   @Override
//   public void create() {
//       //创建 Mesh
//       mesh = new Mesh(true, 3, 3, new VertexAttribute(VertexAttributes.Usage.Position, 3, "a_position"));
//
//       //设置顶点坐标
//       mesh.setVertices(new float[] { -0.5f, -0.5f, 0,
//               0.5f, -0.5f, 0,
//               0, 0.5f, 0 });
//       //设置
//       mesh.setIndices(new short[] { 0, 1, 2 });
//   }
//
//   @Override
//   public void resize(int i, int i2) {
//
//   }
//
//   @Override
//   public void render() {
//       Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//       mesh.render(new ShaderProgram("a", "b"), GL20.GL_TRIANGLES, 0, 3);  //绘制三角形
//
//   }
//
//   @Override
//   public void pause() {
//
//   }
//
//   @Override
//   public void resume() {
//
//   }
//
//   @Override
//   public void dispose() {
//
//   }
//}