package com.xiaojiangtun.util.http;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
public class XmlResponseHandler {
    private static Map<String, ResponseHandler<?>> map = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public static <T> ResponseHandler<T> createResponseHandler(final Class<T> clazz) {
        if (map.containsKey(clazz.getName())) {
            return (ResponseHandler<T>) map.get(clazz.getName());
        } else {
            ResponseHandler<T> responseHandler = (HttpResponse response) ->
            {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    String str = EntityUtils.toString(entity);
                    Header contentType = response.getEntity().getContentType();
                    if (contentType != null && !contentType.toString().matches(".*[uU][tT][fF]-8$")) {
                        str = new String(str.getBytes("iso-8859-1"), "utf-8");
                    }
                    return convertToObject(clazz, new String(str.getBytes("iso-8859-1"), "utf-8"));
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
            map.put(clazz.getName(), responseHandler);
            return responseHandler;
        }
    }

    /**
     * XML to Object
     *
     * @param <T>
     * @param clazz
     * @param xml
     * @return
     */
    static <T> T convertToObject(Class<T> clazz, String xml) {
        return convertToObject(clazz, new StringReader(xml));
    }


    private static final ThreadLocal<Map<Class<?>, Unmarshaller>> uMapLocal = ThreadLocal.withInitial(HashMap::new);


    /**
     * XML to Object
     *
     * @param <T>
     * @param clazz
     * @param reader
     * @return
     */
    @SuppressWarnings("unchecked")
    private static <T> T convertToObject(Class<T> clazz, Reader reader) {
        try {
            Map<Class<?>, Unmarshaller> uMap = uMapLocal.get();
            if (!uMap.containsKey(clazz)) {
                JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                uMap.put(clazz, unmarshaller);
            }
            return (T) uMap.get(clazz).unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
