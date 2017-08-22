package com.wonders.core.progress;


import org.apache.commons.fileupload.ProgressListener;

import javax.servlet.http.HttpSession;

/**
 * Created by c_liqingdong on 2017/5/15.
 */
public class PJProgressListener implements ProgressListener {

    private HttpSession session;

    public PJProgressListener() {

    }

    public PJProgressListener(HttpSession session) {
        this.session = session;
        ProgressEntity ps = new ProgressEntity();
        this.session.setAttribute("upload_ps", ps);
    }

    public void update(long pBytesRead, long pContentLength, int pItems) {
        ProgressEntity ps = (ProgressEntity) session.getAttribute("upload_ps");
        ps.setpBytesRead(pBytesRead);
        ps.setpContentLength(pContentLength);
        ps.setpItems(pItems);
        session.setAttribute("upload_ps", ps);
    }

}
