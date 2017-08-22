package com.wonders.core.progress;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by c_liqingdong on 2017/5/15.
 */
public class PJCommonsMultipartResolver extends CommonsMultipartResolver {

    private HttpServletRequest request;

    protected FileUpload newFileUpload(FileItemFactory fileItemFactory) {
        ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
        upload.setSizeMax(-1);
        if (request != null) {
            HttpSession session = request.getSession();
/*
            new ProgressListener() {
                @Override
                public void update(long pBytesRead, long pContentLength, int pItems) {
                    ProgressEntity ps = new ProgressEntity();
                    ps.setpBytesRead(pBytesRead);
                    ps.setpContentLength(pContentLength);
                    ps.setpItems(pItems);
                    request.getSession().setAttribute("upload_ps", ps);
                }
            };
*/
            PJProgressListener uploadProgressListener = new PJProgressListener(session);
            upload.setProgressListener(uploadProgressListener);
        }
        return upload;
    }

    public MultipartHttpServletRequest resolveMultipart(
            HttpServletRequest request) throws MultipartException {
        this.request = request;
        return super.resolveMultipart(request);
    }


    @Override
    public MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {

        HttpSession session = request.getSession();

        String encoding = "utf-8";
        FileUpload fileUpload = prepareFileUpload(encoding);

        PJProgressListener uploadProgressListener = new PJProgressListener(session);
        fileUpload.setProgressListener(uploadProgressListener);

        try {
            List<FileItem> fileItems = ((ServletFileUpload) fileUpload).parseRequest(request);
            return parseFileItems(fileItems, encoding);
        } catch (FileUploadBase.SizeLimitExceededException ex) {
            throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(), ex);
        } catch (FileUploadException ex) {
            throw new MultipartException("Could not parse multipart servlet request", ex);
        }
    }

}
