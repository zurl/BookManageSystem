<template>
    <div class="root">
        <admin-page-header></admin-page-header>
        <admin-page-body></admin-page-body>
        <admin-page-footer></admin-page-footer>
    </div>
</template>
<script>
    import AdminPageHeader from './components/AdminHeader.vue'
    import AdminPageBody from './components/AdminBody.vue'
    import AdminPageFooter from './components/AdminFooter.vue'
export default {
    components: {
        'admin-page-header' : AdminPageHeader,
        'admin-page-body' : AdminPageBody,
        'admin-page-footer' : AdminPageFooter
    }
}
</script>
<style>

</style>