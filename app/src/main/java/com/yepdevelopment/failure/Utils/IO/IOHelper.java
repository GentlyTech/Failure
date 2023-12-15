package com.yepdevelopment.failure.Utils.IO;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.webkit.MimeTypeMap;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * A helper class for reading and writing files.
 */
public class IOHelper {
    /**
     * This class should not be instantiated.
     */
    private IOHelper() {

    }

    /**
     * Gets a File handle to the specified filename.
     *
     * @param applicationContext an Android Context object so that resources can be retrieved
     * @param filename           the name of the file
     * @return a File handle pointing to the save location. This file may or may not exist, so please do some checks before using it.
     */
    public static File open(Context applicationContext, String filename) {
        return new File(applicationContext.getFilesDir(), filename);
    }

    /**
     * Gets a File handle to the specified filename, making all subdirectories leading up to the final destination.
     *
     * @param applicationContext an Android Context object so that resources can be retrieved
     * @param filename           the name of the file, including parent directories
     * @return a File handle pointing to the save location. This file may or may not exist, so please do some checks before using it.
     */
    public static File openWithDirs(Context applicationContext, String filename) {
        File handle = new File(applicationContext.getFilesDir(), filename);
        handle.getParentFile().mkdirs();
        return handle;
    }

    /**
     * Attempts to open a stream to a raw resource.
     *
     * @param applicationContext an Android Context object so that resources can be retrieved
     * @param resourceId         the ID of the raw resource to fetch
     * @return an InputStream to the raw resource
     * @throws Resources.NotFoundException if the raw resource with the specified ID could not be found
     */
    public static InputStream openResource(Context applicationContext, int resourceId) throws Resources.NotFoundException {
        Resources resources = applicationContext.getResources();
        return resources.openRawResource(resourceId);
    }

    /**
     * Attempts to open a stream to a resource referenced by an Android URI.
     *
     * @param applicationContext an Android Context object so that resources can be retrieved
     * @param uri                the URI of the resource to fetch
     * @return an InputStream to the resource
     */
    public static InputStream openResourceFromUri(Context applicationContext, Uri uri) throws FileNotFoundException {
        return applicationContext.getContentResolver().openInputStream(uri);
    }

    /**
     * Attempts to get a suitable file extension for the file specified by the given URI.
     *
     * @param applicationContext an Android Context object so that resources can be retrieved
     * @param uri                the URI of the resource to get the file extension of
     * @return a String containing a suitable file extension
     */
    public static String getFileExtension(Context applicationContext, Uri uri) throws FileNotFoundException {
        ContentResolver contentResolver = applicationContext.getContentResolver();
        MimeTypeMap mimeType = MimeTypeMap.getSingleton();
        return mimeType.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    /**
     * Attempts to save the given JSON data to disk.
     *
     * @param applicationContext an Android Context object so that resources can be retrieved
     * @param fileHandle         a handle to the file to write to
     * @param data               the data to write to the file
     * @throws IOException if there was a problem writing to the file
     */
    public static void writeJson(Context applicationContext, File fileHandle, JSONObject data) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(fileHandle);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        writer.write(data.toString());
        writer.close();
    }

    /**
     * Attempts to save the given raw data to disk.
     *
     * @param applicationContext an Android Context object so that resources can be retrieved
     * @param fileHandle         a handle to the file to write to
     * @param data               the data to write to the file
     * @throws IOException if there was a problem writing to the file
     */
    public static void writeRaw(Context applicationContext, File fileHandle, byte[] data) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(fileHandle);
        outputStream.write(data);
        outputStream.close();
    }

    /**
     * Attempts to convert the given InputStream to a byte array.
     *
     * @param stream the InputStream to convert
     * @return a byte array representation of the data
     * @throws IOException if there was a problem writing to the file
     */
    public static byte[] streamToByteArray(InputStream stream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream(1024);
        byte[] chunk = new byte[4096];
        int lastNBytesRead;

        while ((lastNBytesRead = stream.read(chunk, 0, chunk.length)) != -1) {
            buffer.write(chunk, 0, lastNBytesRead);
        }

        return buffer.toByteArray();
    }

    /**
     * Attempts to retrieve and parse the given JSON file from disk.
     *
     * @param fileHandle the file to parse
     * @return the deserialized JSON object that was saved on disk, or null if the file was not found
     * @throws IOException   if there was a problem reading from the file
     * @throws JSONException if the contents of the file was not valid JSON
     */
    public static JSONObject readJson(File fileHandle) throws IOException, JSONException {
        if (!fileHandle.exists()) return null;

        FileInputStream inputStream = new FileInputStream(fileHandle);
        Scanner scanner = new Scanner(inputStream);
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine());
        }
        scanner.close();
        return new JSONObject(builder.toString());
    }

    /**
     * Attempts to retrieve and parse the given JSON file from a stream.
     *
     * @param inputStream the file to parse
     * @return the deserialized JSON object from the stream, or null if deserialization failed
     * @throws JSONException if the contents of the stream was not valid JSON
     */
    public static JSONObject readJson(InputStream inputStream) throws JSONException {
        if (inputStream == null) return null;

        Scanner scanner = new Scanner(inputStream);
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine());
        }
        scanner.close();
        return new JSONObject(builder.toString());
    }

    /**
     * Attempts to delete the given file from disk.
     *
     * @param fileHandle the handle to the file to delete
     * @return true if the file was deleted, and false if an error occurred or there was no file to begin with
     */
    public static boolean deleteFile(File fileHandle) {
        if (fileHandle.exists())
            return fileHandle.delete();

        return false;

    }
}