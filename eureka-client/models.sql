

ALTER TABLE ONLY public."model"
    ADD CONSTRAINT model_pkey PRIMARY KEY (model_id);



ALTER TABLE ONLY public."model"
    ADD CONSTRAINT model_item_id_fkey FOREIGN KEY (model_name) REFERENCES public."item"(model);


