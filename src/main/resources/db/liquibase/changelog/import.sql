/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  kwamaGithub
 * Created: 16 mars 2022
 */

INSERT INTO manage.parameters(code, label) 
values
('LISTING_STATE','listing possible states'),
('TIER_LIMIT','Number of published listings a dealer can have online')
ON CONFLICT (code) DO UPDATE 
SET label= EXCLUDED.label;


INSERT INTO manage.values(id, label,parameter_code,value)
values 
('DF','Draft','LISTING_STATE','DFT'),
('PBD','Published','LISTING_STATE','PBHD'),
('TL','Tier limit','TIER_LIMIT','TRL')
ON CONFLICT (id) DO UPDATE 
SET label= EXCLUDED.label,
parameter_code=EXCLUDED.parameter_code,
value=EXCLUDED.value;

INSERT INTO manage.dealer(id, name)
values 
('HDAI','Hyundai'),
('HD','Honda'),
('GC','GMC'),
('II','INFINITI'),
('JR','JAGUAR'),
('JP','Jeep'),
('NN','NISSAN')
ON CONFLICT (id) DO UPDATE 
SET name=EXCLUDED.name;
