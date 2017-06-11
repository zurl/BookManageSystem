import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css'
import Vuex from 'vuex'
import VueRouter from 'vue-router'


import App from './App.vue'
import AdminPage from './pages/admin/Admin.vue'
import LoginPage from './pages/login/Login.vue'
import TemplatePage from './pages/Template.vue'
import MetaDataPage from './pages/MetaData.vue'

import IndexPage from './pages/index/Index.vue'

import QueryPage from './pages/index/Query.vue';
import AdminManageCardPage from './pages/admin/subpages/ManageCard.vue';
import AdminManageBookPage from './pages/admin/subpages/ManageBook.vue';
import AdminPutBookPage from './pages/admin/subpages/PutBook.vue';
import AdminManageAdminPage from './pages/admin/subpages/ManageAdmin.vue';
const routes = [
    {
        path: '/',
        component: IndexPage
    },
    {
        path: '/login',
        component: LoginPage
    },
    {
        path: '/query',
        component: QueryPage
    },
    {
        path: '/admin',
        component: AdminPage,
        children: [
            {
                path: 'query',
                component: QueryPage
            },
            {
                path: 'manage-card',
                component: AdminManageCardPage
            },
            {
                path: 'manage-book',
                component: AdminManageBookPage
            },
            {
                path: 'manage-admin',
                component: AdminManageAdminPage
            },
            {
                path: 'put-book',
                component: AdminPutBookPage
            },
            {
                path: 'template',
                component: TemplatePage
            },
            {
                path: 'metadata',
                component: MetaDataPage
            }
        ]
    }
];


const router = new VueRouter({
    routes
});

Vue.use(Vuex);
Vue.use(ElementUI);
Vue.use(VueRouter);

const store = new Vuex.Store({
    state: {
        count: 0
    },
    mutations: {
        increment (state) {
            state.count++
        }
    }
});


new Vue({
    el: '#app',
    router,
    render: h => h(App)
});
