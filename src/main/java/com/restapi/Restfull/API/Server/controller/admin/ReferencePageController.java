package com.restapi.Restfull.API.Server.controller.admin;

import com.restapi.Restfull.API.Server.interfaces.controller.ControllerInitialize;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Log4j2
@Controller
public class ReferencePageController implements ControllerInitialize {
    private ModelAndView modelAndView;

    @Override
    public void init(String method) {
        log.info(method);
    }

    @RequestMapping(value = "/admin/cropper.do", method = RequestMethod.GET)
    public ModelAndView Cropper() {
        modelAndView = new ModelAndView("/pages/advanced-ui/cropper");
        init("GET Cropper");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/owlcarousel.do", method = RequestMethod.GET)
    public ModelAndView OWLCarousel() {
        modelAndView = new ModelAndView("/pages/advanced-ui/owl-carousel");
        init("GET OWLCarousel");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/sweet-alert.do", method = RequestMethod.GET)
    public ModelAndView SweetAlert() {
        modelAndView = new ModelAndView("/pages/advanced-ui/sweet-alert");
        init("GET SweetAlert");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/calendar.do", method = RequestMethod.GET)
    public ModelAndView Calendar() {
        modelAndView = new ModelAndView("/pages/apps/calendar");
        init("GET Calendar");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/chat.do", method = RequestMethod.GET)
    public ModelAndView Chat() {
        modelAndView = new ModelAndView("/pages/apps/chat");
        init("GET Chat");
        return modelAndView;
    }


    @RequestMapping(value = "/admin/register.do", method = RequestMethod.GET)
    public ModelAndView Register() {
        modelAndView = new ModelAndView("/pages/auth/register");
        init("GET Register");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/apex.do", method = RequestMethod.GET)
    public ModelAndView Apex() {
        modelAndView = new ModelAndView("/pages/charts/apex");
        init("GET Apex");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/chartjs.do", method = RequestMethod.GET)
    public ModelAndView ChartJs() {
        modelAndView = new ModelAndView("/pages/charts/chartjs");
        init("GET ChartJs");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/flot.do", method = RequestMethod.GET)
    public ModelAndView Flot() {
        modelAndView = new ModelAndView("/pages/charts/flot");
        init("GET Flot");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/morrisjs.do", method = RequestMethod.GET)
    public ModelAndView MorrisJs() {
        modelAndView = new ModelAndView("/pages/charts/morrisjs");
        init("GET MorrisJs");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/peity.do", method = RequestMethod.GET)
    public ModelAndView Peity() {
        modelAndView = new ModelAndView("/pages/charts/peity");
        init("GET Peity");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/sparkline.do", method = RequestMethod.GET)
    public ModelAndView Sparkline() {
        modelAndView = new ModelAndView("/pages/charts/sparkline");
        init("GET Sparkline");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/compose.do", method = RequestMethod.GET)
    public ModelAndView Compose() {
        modelAndView = new ModelAndView("/pages/email/compose");
        init("GET Compose");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/inbox.do", method = RequestMethod.GET)
    public ModelAndView Inbox() {
        modelAndView = new ModelAndView("/pages/email/inbox");
        init("GET Inbox");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/read.do", method = RequestMethod.GET)
    public ModelAndView Read() {
        modelAndView = new ModelAndView("/pages/email/read");
        init("GET Read");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/advanced.do", method = RequestMethod.GET)
    public ModelAndView Advanced() {
        modelAndView = new ModelAndView("/pages/forms/advanced-elements");
        init("GET Advanced");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/basic.do", method = RequestMethod.GET)
    public ModelAndView Basic() {
        modelAndView = new ModelAndView("/pages/forms/basic-elements");
        init("GET Basic");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/editors.do", method = RequestMethod.GET)
    public ModelAndView Editors() {
        modelAndView = new ModelAndView("/pages/forms/editors");
        init("GET Editors");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/wizard.do", method = RequestMethod.GET)
    public ModelAndView Wizard() {
        modelAndView = new ModelAndView("/pages/forms/wizard");
        init("GET Wizard");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/blank.do", method = RequestMethod.GET)
    public ModelAndView Blank() {
        modelAndView = new ModelAndView("/pages/general/blank-page");
        init("GET Blank");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/faq.do", method = RequestMethod.GET)
    public ModelAndView Faq() {
        modelAndView = new ModelAndView("/pages/general/faq");
        init("GET Faq");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/invoice.do", method = RequestMethod.GET)
    public ModelAndView Invoice() {
        modelAndView = new ModelAndView("/pages/general/invoice");
        init("GET Invoice");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/pricing.do", method = RequestMethod.GET)
    public ModelAndView Pricing() {
        modelAndView = new ModelAndView("/pages/general/pricing");
        init("GET Pricing");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/profile.do", method = RequestMethod.GET)
    public ModelAndView Profile() {
        modelAndView = new ModelAndView("/pages/general/profile");
        init("GET Profile");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/timeline.do", method = RequestMethod.GET)
    public ModelAndView Timeline() {
        modelAndView = new ModelAndView("/pages/general/timeline");
        init("GET Timeline");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/feather.do", method = RequestMethod.GET)
    public ModelAndView Feather() {
        modelAndView = new ModelAndView("/pages/icons/feather-icons");
        init("GET Feather");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/flag.do", method = RequestMethod.GET)
    public ModelAndView Flag() {
        modelAndView = new ModelAndView("/pages/icons/flag-icons");
        init("GET Flag");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/mdi.do", method = RequestMethod.GET)
    public ModelAndView Mdi() {
        modelAndView = new ModelAndView("/pages/icons/mdi-icons");
        init("GET Mdi");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/basictable.do", method = RequestMethod.GET)
    public ModelAndView BasicTable() {
        modelAndView = new ModelAndView("/pages/tables/basic-table");
        init("GET BasicTable");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/datatable.do", method = RequestMethod.GET)
    public ModelAndView DataTable() {
        modelAndView = new ModelAndView("/pages/tables/data-table");
        init("GET DataTable");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/alerts.do", method = RequestMethod.GET)
    public ModelAndView Alerts() {
        modelAndView = new ModelAndView("/pages/ui-components/alerts");
        init("GET Alerts");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/badges.do", method = RequestMethod.GET)
    public ModelAndView Badges() {
        modelAndView = new ModelAndView("/pages/ui-components/badges");
        init("GET Badges");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/breadcrumbs.do", method = RequestMethod.GET)
    public ModelAndView Breadcrumbs() {
        modelAndView = new ModelAndView("/pages/ui-components/breadcrumbs");
        init("GET Breadcrumbs");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/buttongroup.do", method = RequestMethod.GET)
    public ModelAndView ButtonGroup() {
        modelAndView = new ModelAndView("/pages/ui-components/button-group");
        init("GET ButtonGroup");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/buttons.do", method = RequestMethod.GET)
    public ModelAndView Buttons() {
        modelAndView = new ModelAndView("/pages/ui-components/buttons");
        init("GET Buttons");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/cards.do", method = RequestMethod.GET)
    public ModelAndView Cards() {
        modelAndView = new ModelAndView("/pages/ui-components/cards");
        init("GET Cards");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/carousel.do", method = RequestMethod.GET)
    public ModelAndView Carousel() {
        modelAndView = new ModelAndView("/pages/ui-components/carousel");
        init("GET Carousel");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/collapse.do", method = RequestMethod.GET)
    public ModelAndView Collapse() {
        modelAndView = new ModelAndView("/pages/ui-components/collapse");
        init("GET Collapse");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/dropdowns.do", method = RequestMethod.GET)
    public ModelAndView Dropdowns() {
        modelAndView = new ModelAndView("/pages/ui-components/dropdowns");
        init("GET Dropdowns");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/listgroup.do", method = RequestMethod.GET)
    public ModelAndView ListGroup() {
        modelAndView = new ModelAndView("/pages/ui-components/list-group");
        init("GET ListGroup");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/mediaobject.do", method = RequestMethod.GET)
    public ModelAndView MediaObject() {
        modelAndView = new ModelAndView("/pages/ui-components/media-object");
        init("GET MediaObject");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/modal.do", method = RequestMethod.GET)
    public ModelAndView Modal() {
        modelAndView = new ModelAndView("/pages/ui-components/modal");
        init("GET Modal");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/navbar.do", method = RequestMethod.GET)
    public ModelAndView Navbar() {
        modelAndView = new ModelAndView("/pages/ui-components/navbar");
        init("GET Navbar");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/navs.do", method = RequestMethod.GET)
    public ModelAndView Navs() {
        modelAndView = new ModelAndView("/pages/ui-components/navs");
        init("GET Navs");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/pagination.do", method = RequestMethod.GET)
    public ModelAndView Pagination() {
        modelAndView = new ModelAndView("/pages/ui-components/pagination");
        init("GET Pagination");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/popover.do", method = RequestMethod.GET)
    public ModelAndView PopOver() {
        modelAndView = new ModelAndView("/pages/ui-components/popover");
        init("GET PopOver");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/progress.do", method = RequestMethod.GET)
    public ModelAndView Progress() {
        modelAndView = new ModelAndView("/pages/ui-components/progress");
        init("GET Progress");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/scrollbar.do", method = RequestMethod.GET)
    public ModelAndView ScrollBar() {
        modelAndView = new ModelAndView("/pages/ui-components/scrollbar");
        init("GET ScrollBar");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/scrollspy.do", method = RequestMethod.GET)
    public ModelAndView ScrollSpy() {
        modelAndView = new ModelAndView("/pages/ui-components/scrollspy");
        init("GET ScrollSpy");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/spinners.do", method = RequestMethod.GET)
    public ModelAndView Spinners() {
        modelAndView = new ModelAndView("/pages/ui-components/spinners");
        init("GET Spinners");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/tooltips.do", method = RequestMethod.GET)
    public ModelAndView Tooltips() {
        modelAndView = new ModelAndView("/pages/ui-components/tooltips");
        init("GET Tooltips");
        return modelAndView;
    }
}
