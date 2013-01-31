package com.mvcvideoplayer;
import java.io.File;

import com.mvcvideoplayer.utils.FfmpegUtils;


public class FfmpegUtilsTest {

	public static void main(String[] args) {
		
		//FfmpegUtils ffmpeg = new FfmpegUtils("/opt/local/bin/ffmpeg");
		//ffmpeg.generateThumbnails(
		//		"/Users/serraventura/Library/apache-tomcat-7.0.23/webapps/upload-videos/teste2.flv", 
		//		new File( "/Users/serraventura/Library/apache-tomcat-7.0.23/webapps/upload-videos/" ), 
		//		320, 
		//		240, 
		//		1, true);
		
		String texto = "123456789";
		System.out.println(texto.length());
		System.out.println(texto.substring(0, 10));
		
	}

	/*
	
	private FfmpegUtils ffmpeg;

    @Before
    public void setUp()
    {
        ffmpeg = new FfmpegUtils( "ffmpeg" );
    }

    @Test
    public void testDuration()
    {
        String duration = ffmpeg.getDuration( "/home/shaines/Videos/myvideo.avi" );
        Assert.assertNotNull( duration );
        Assert.assertEquals( "Duration is not correct", "00:42:53.59", duration );
    }

    @Test
    public void testThumbnails()
    {
        ffmpeg.generateThumbnails("/home/shaines/Videos/myvideo.avi", new File( "/home/shaines/Videos/Thumbs" ), 480, 480, 6 );
        Assert.assertTrue( true );
    }

	*/
	
}
