package com.example.search.filter;

import com.example.search.threadlocal.MyThreadLocal;
import com.netflix.client.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.slf4j.MDC;
import java.util.UUID;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class MdcFilter implements Filter {

    @Override
    public  void init(FilterConfig fc) throws ServletException{
        //nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        if(request instanceof HttpServletRequest){
            HttpServletRequest req = (HttpServletRequest)request;
            //String correlationId = UUID.randomUUID().toString();

            MyThreadLocal myThreadLocal = new MyThreadLocal();
            myThreadLocal.startTransaction(getCorrelationId());
            MDC.put("CorrelationId", getCorrelationId());
        }
        try {
            //MDC.put("CorrelationId", getCorrelationId());
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove("CorrelationId");
            MyThreadLocal.endTransaction();
        }
    }

    private String getCorrelationId() {
        return UUID.randomUUID().toString();
    }
}
