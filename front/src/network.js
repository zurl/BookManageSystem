/**
 * Created by zcy on 2017/2/9.
 */

export const server_url = "http://localhost:8080";

export const Network = {
    getURL : function( link ){
        return link.replace(/^[a-z]*:\/\/[^\/]*\//,'/');
    },
    getId : function ( obj ){
        const arr = obj["_links"]["self"]["href"].split('/');
        return arr[arr.length - 1];
    },
    get: function(resource, callback, error = ()=>{}) {
        fetch(server_url + resource, {credentials: 'include',method: 'GET'}).then(
            item => item.json().then(
                json =>{
                    if(json.status && json.status != 200)error(json.message);
                    else callback(json);
                }
            )
        ).catch(error);
    },
    post: function(resource, payload, callback, error = ()=>{}) {
        fetch(server_url + resource, {
                headers : new Headers({
                    "Content-Type": "application/json"
                }),
                credentials: 'include',
                body: JSON.stringify(payload),
                method: 'POST'
            } ).then(
            item => item.json().then(
                json => {
                    if(json.status && json.status != 200)error(json.message);
                    else callback(json);
                }
            )
        ).catch(error);
    },
    put: function(resource, payload, callback, error= ()=>{}) {
        fetch(server_url + resource, {
            headers : new Headers({
                "Content-Type": "application/json"
            }),
            credentials: 'include',
            body: JSON.stringify(payload),
            method: 'PUT'
        } ).then(
            item => item.json().then(
                json => {
                    if(json.status && json.status != 200)error(json.message);
                    else callback(json);
                }
            )
        ).catch(error);
    },
    del: function(resource, callback, error = ()=>{}) {
        fetch(server_url + resource, { credentials: 'include',method: 'DELETE' } ).then(
           item => {
               if(item.status && item.status != 200) error(item.message);
               else callback(item);
           }
        ).catch(error);
    }
};
