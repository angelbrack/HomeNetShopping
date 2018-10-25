package prjframework.common.util;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifIFD0Directory;

public class ImageOrientationUtil {

	/**
	 * Checks the orientation of the image and corrects it if necessary.
	 * <p>If the orientation of the image does not need to be corrected, no operation will be performed.</p>
	 * @param inputStream
	 * @return
	 * @throws ImageProcessingException
	 * @throws IOException
	 * @throws MetadataException
	 */
	public static BufferedImage correctOrientation(File file) throws ImageProcessingException, IOException, MetadataException {
	    
		InputStream inputStream = null;
		Metadata metadata = null;
		
		try {
			inputStream = new FileInputStream(file);
			metadata = ImageMetadataReader.readMetadata(inputStream);
		    if(metadata != null) {
		        if(metadata.containsDirectoryOfType(ExifIFD0Directory.class)) {
		            // Get the current orientation of the image
		            Directory directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
		            int orientation = directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);
		            System.out.println("orientation=["+orientation+"]");
		            // Create a buffered image from the input stream
		            BufferedImage bimg = ImageIO.read(file);
	
	
		            // Get the current width and height of the image
		            int[] imageSize = {bimg.getWidth(), bimg.getHeight()};
		            int width = imageSize[0];
		            int height = imageSize[1];
	
		            // Determine which correction is needed
		            AffineTransform t = new AffineTransform();
		            switch(orientation) {
		            case 1:
		                // no correction necessary skip and return the image
		                return bimg;
		            case 2: // Flip X
		                t.scale(-1.0, 1.0);
		                t.translate(-width, 0);
		                return transform(bimg, t);
		            case 3: // PI rotation 
		                t.translate(width, height);
		                t.rotate(Math.PI);
		                return transform(bimg, t);
		            case 4: // Flip Y
		                t.scale(1.0, -1.0);
		                t.translate(0, -height);
		                return transform(bimg, t);
		            case 5: // - PI/2 and Flip X
		                t.rotate(-Math.PI / 2);
		                t.scale(-1.0, 1.0);
		                return transform(bimg, t);
		            case 6: // -PI/2 and -width
		                t.translate(height, 0);
		                t.rotate(Math.PI / 2);
		                return transform(bimg, t);
		            case 7: // PI/2 and Flip
		                t.scale(-1.0, 1.0);
		                t.translate(height, 0);
		                t.translate(0, width);
		                t.rotate(  3 * Math.PI / 2);
		                return transform(bimg, t);
		            case 8: // PI / 2
		                t.translate(0, width);
		                t.rotate(  3 * Math.PI / 2);
		                return transform(bimg, t);
		            }
		        }
		    }
		} catch (ImageProcessingException e) {
			throw new ImageProcessingException(e.getMessage());
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} catch (MetadataException e) {
			throw new MetadataException(e.getMessage());
		} finally {
			inputStream.close();
		}
	    return null;
	}

	/**
	 * Performs the tranformation
	 * @param bimage
	 * @param transform
	 * @return
	 * @throws IOException
	 */
	private static BufferedImage transform(BufferedImage bimage, AffineTransform transform) throws IOException {
	    // Create an transformation operation
	    AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BICUBIC);

	    // Create an instance of the resulting image, with the same width, height and image type than the referenced one
	    BufferedImage destinationImage = new BufferedImage( bimage.getWidth(), bimage.getHeight(), bimage.getType() );
	    op.filter(bimage, destinationImage);

	   return destinationImage;
	}
}
