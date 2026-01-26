package org.example.api_gateway.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;

import java.net.URI;

@Configuration
public class GatewayRouteLoggingFilter {

    private static final Logger log = LoggerFactory.getLogger(GatewayRouteLoggingFilter.class);

    @Bean
    public GlobalFilter routeAndChosenInstanceLoggingFilter() {
        return (ServerWebExchange exchange, GatewayFilterChain chain) ->
                chain.filter(exchange)
                        .doOnSuccess(v -> logTarget(exchange))
                        .doOnError(e -> logTarget(exchange));
    }

    @SuppressWarnings("unchecked")
    private void logTarget(ServerWebExchange exchange) {
        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);

        URI requestUrl = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);

        // To jest najważniejsze: response z loadbalancera, zawiera ServiceInstance
        Response<ServiceInstance> lbResponse =
                exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_LOADBALANCER_RESPONSE_ATTR);

        ServiceInstance si = (lbResponse != null) ? lbResponse.getServer() : null;

        String method = String.valueOf(exchange.getRequest().getMethod());
        String path = exchange.getRequest().getURI().getPath();

        String routeId = route != null ? route.getId() : "<none>";
        String service = route != null ? String.valueOf(route.getUri()) : "<none>";
        String url = requestUrl != null ? String.valueOf(requestUrl) : "<unresolved>";

        String instanceId = si != null ? safe(si.getInstanceId()) : "<none>";
        String host = si != null ? safe(si.getHost()) : "<none>";
        String ip = (si != null && si.getHost() != null && si.getHost().matches("\\d+\\.\\d+\\.\\d+\\.\\d+"))
                ? si.getHost()
                : "<n/a>";
        int port = si != null ? si.getPort() : -1;

        log.info("[GW] {} {} -> routeId={} service={} url={} chosenInstanceId={} host={} ip={} port={}",
                method, path, routeId, service, url, instanceId, host, ip, port);
    }

    private String safe(String s) {
        return (s == null || s.isBlank()) ? "<none>" : s;
    }
}