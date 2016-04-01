/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Gabriel Cauchis
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.github.gcauchis.scalablepress.services;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.gcauchis.scalablepress.model.Event;
import com.github.gcauchis.scalablepress.model.QueryEvent;

/**
 * The Event API helps you keep track of event of your orders.
 * @author gcauchis
 * @see https://scalablepress.com/docs/#event-api
 */
@Service
public class EventServices extends AbstractRestServices {

    /**
     * Events contain useful information about the state of your order.
     *
     * @param query the query
     * @return Returns an array of event objects.
     * @see https://scalablepress.com/docs/#query-events
     */
    public List<Event> queryEvents(QueryEvent query) {
        StringBuilder args = new StringBuilder();
        if (StringUtils.isNotBlank(query.getOrderId())) {
            args.append("orderId=").append(query.getOrderId());
        }
        if (StringUtils.isNotBlank(query.getName())) {
            if (args.length() > 0) {
                args.append("&");
            }
            args.append("name=").append(query.getName());
        }
        if (StringUtils.isNotBlank(query.getStart())) {
            if (args.length() > 0) {
                args.append("&");
            }
            args.append("start=").append(query.getStart());
        }
        if (StringUtils.isNotBlank(query.getEnd())) {
            if (args.length() > 0) {
                args.append("&");
            }
            args.append("end=").append(query.getEnd());
        }
        if (StringUtils.isNotBlank(query.getItemIndex())) {
            if (args.length() > 0) {
                args.append("&");
            }
            args.append("itemIndex=").append(query.getItemIndex());
        }
        if (StringUtils.isNotBlank(query.getItemName())) {
            if (args.length() > 0) {
                args.append("&");
            }
            args.append("itemName=").append(query.getItemName());
        }
        if (StringUtils.isNotBlank(query.getSort())) {
            if (args.length() > 0) {
                args.append("&");
            }
            args.append("sort=").append(query.getSort());
        }
        return Arrays.asList(get("event?" + args.toString(), Event[].class));
    }
    
    /**
     * Provide the eventId in order to receive the details of an event.
     * 
     * @return event object.
     * @seehttps://scalablepress.com/docs/#retrieve-single-event
     */
    public Event retreive(String eventId) {
        return get("event/" + eventId, Event.class);
    }
}
