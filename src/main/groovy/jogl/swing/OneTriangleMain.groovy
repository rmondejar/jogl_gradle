package jogl.swing

import groovy.transform.CompileStatic

import com.jogamp.opengl.GLAutoDrawable
import com.jogamp.opengl.GLEventListener
import com.jogamp.opengl.GLProfile
import com.jogamp.opengl.GLCapabilities
import com.jogamp.opengl.awt.GLCanvas
import javax.swing.JFrame

import java.awt.BorderLayout
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent

/**
 * A minimal program that draws with JOGL in a Swing JFrame using the AWT GLCanvas.
 *
 * @author Wade Walker
 */
@CompileStatic
public class OneTriangleMain {

    public static void main( String [] args ) {

        GLProfile glprofile = GLProfile.default
        GLCapabilities glcapabilities = new GLCapabilities( glprofile )
        final GLCanvas glcanvas = new GLCanvas( glcapabilities )

        glcanvas.addGLEventListener( new GLEventListener() {

            @Override
            public void reshape( GLAutoDrawable glautodrawable, int x, int y, int width, int height ) {
                OneTriangle.setup( glautodrawable.getGL().getGL2(), width, height )
            }

            @Override
            public void init( GLAutoDrawable glautodrawable ) {
            }

            @Override
            public void dispose( GLAutoDrawable glautodrawable ) {
            }

            @Override
            public void display( GLAutoDrawable glautodrawable ) {
                OneTriangle.render( glautodrawable.getGL().getGL2(), glautodrawable.surfaceWidth, glautodrawable.surfaceHeight )
            }
        })

        final JFrame jframe = new JFrame( "One Triangle Swing GLCanvas" )
        jframe.addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent windowevent ) {
                jframe.dispose()
                System.exit( 0 )
            }
        })

        jframe.contentPane.add( glcanvas, BorderLayout.CENTER )
        jframe.setSize( 640, 480 )
        jframe.visible = true
    }
}